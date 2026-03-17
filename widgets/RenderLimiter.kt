package widgets

import java.util.concurrent.ConcurrentHashMap

/**
 * Guards widget re-render and state recalculation frequency.
 */
class RenderLimiter(private val nowMs: () -> Long = { System.currentTimeMillis() }) {
    private val lastRenderByWidget = ConcurrentHashMap<String, Long>()
    private val lastStateHashByWidget = ConcurrentHashMap<String, Int>()

    fun shouldRender(widgetId: String, stateHash: Int, minRenderIntervalMs: Long = 1_000): Boolean {
        val now = nowMs()
        val lastRender = lastRenderByWidget[widgetId] ?: 0L
        val lastHash = lastStateHashByWidget[widgetId]

        val unchangedState = (lastHash != null && lastHash == stateHash)
        val tooSoon = (now - lastRender) < minRenderIntervalMs

        if (unchangedState || tooSoon) return false

        lastStateHashByWidget[widgetId] = stateHash
        lastRenderByWidget[widgetId] = now
        return true
    }
}
