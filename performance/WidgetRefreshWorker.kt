package performance

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import java.util.concurrent.ConcurrentHashMap

private const val INPUT_KEY_REFRESH_NAME = "refresh_name"

/**
 * Integration hook for fallback refresh jobs.
 *
 * App integration should register refresh actions per widget family (or a default handler)
 * so WorkManager fallback jobs can execute the same update pipeline used by event-driven updates.
 */
object WidgetRefreshRegistry {
    private val refreshers = ConcurrentHashMap<String, suspend (Context) -> Unit>()

    @Volatile
    private var defaultRefresher: (suspend (Context) -> Unit)? = null

    fun register(uniqueName: String, refresher: suspend (Context) -> Unit) {
        refreshers[uniqueName] = refresher
    }

    fun registerDefault(refresher: suspend (Context) -> Unit) {
        defaultRefresher = refresher
    }

    fun clear(uniqueName: String) {
        refreshers.remove(uniqueName)
    }

    fun clearAll() {
        refreshers.clear()
        defaultRefresher = null
    }

    suspend fun refresh(context: Context, uniqueName: String?): Boolean {
        val namedRefresher = uniqueName?.let(refreshers::get)
        val refresher = namedRefresher ?: defaultRefresher ?: return false
        refresher(context)
        return true
    }
}

class WidgetRefreshWorker(
    appContext: Context,
    params: WorkerParameters
) : CoroutineWorker(appContext, params) {
    override suspend fun doWork(): Result {
        val refreshName = inputData.getString(INPUT_KEY_REFRESH_NAME)
        val refreshed = WidgetRefreshRegistry.refresh(applicationContext, refreshName)

        return if (refreshed) {
            Result.success()
        } else {
            Result.failure()
        }
    }

    companion object {
        const val REFRESH_NAME_KEY: String = INPUT_KEY_REFRESH_NAME
    }
}
