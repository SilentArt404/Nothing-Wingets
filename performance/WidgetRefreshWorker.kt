package performance

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

class WidgetRefreshWorker(
    appContext: Context,
    params: WorkerParameters
) : CoroutineWorker(appContext, params) {
    override suspend fun doWork(): Result {
        // fallback refresh for long-idle scenarios
        // concrete updater should be injected in app integration layer
        return Result.success()
    }
}
