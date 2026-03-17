package performance

import android.content.Context
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.TimeUnit

/**
 * Event-first update strategy:
 * 1) event-driven updates from launcher/system callbacks
 * 2) WorkManager fallback with battery/network constraints
 * 3) cache with TTL to avoid repetitive data fetch
 * 4) debouncing to collapse burst events
 */
class UpdateStrategy(
    private val context: Context,
    private val clockMs: () -> Long = { System.currentTimeMillis() }
) {
    private val cache = ConcurrentHashMap<String, CacheEntry>()
    private val lastEventTs = ConcurrentHashMap<String, Long>()

    data class CacheEntry(val payload: Any, val ts: Long, val ttlMs: Long)

    fun onEvent(eventKey: String, minIntervalMs: Long = 5_000, action: () -> Unit) {
        val now = clockMs()
        val previous = lastEventTs[eventKey] ?: 0L
        if (now - previous < minIntervalMs) return // debounce
        lastEventTs[eventKey] = now
        action()
    }

    fun <T : Any> getOrFetch(cacheKey: String, ttlMs: Long, fetch: () -> T): T {
        val now = clockMs()
        val current = cache[cacheKey]
        if (current != null && now - current.ts <= current.ttlMs) {
            @Suppress("UNCHECKED_CAST")
            return current.payload as T
        }
        val fresh = fetch()
        cache[cacheKey] = CacheEntry(fresh, now, ttlMs)
        return fresh
    }

    fun scheduleConstrainedRefresh(uniqueName: String, delayMinutes: Long = 15): WorkRequest {
        val constraints = Constraints.Builder()
            .setRequiresBatteryNotLow(true)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val request = OneTimeWorkRequestBuilder<WidgetRefreshWorker>()
            .setConstraints(constraints)
            .setInitialDelay(delayMinutes, TimeUnit.MINUTES)
            .setInputData(
                Data.Builder()
                    .putString(WidgetRefreshWorker.REFRESH_NAME_KEY, uniqueName)
                    .build()
            )
            .addTag(uniqueName)
            .build()

        WorkManager.getInstance(context)
            .enqueueUniqueWork(uniqueName, ExistingWorkPolicy.REPLACE, request)
        return request
    }
}
