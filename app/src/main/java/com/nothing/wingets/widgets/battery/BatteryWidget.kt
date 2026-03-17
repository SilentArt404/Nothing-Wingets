package com.nothing.wingets.widgets.battery

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import androidx.compose.runtime.Composable
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.provideContent
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.fillMaxSize
import androidx.glance.text.Text

class BatteryWidget : GlanceAppWidget() {
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            BatteryContent(level = readBatteryLevel(context))
        }
    }
}

class BatteryWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = BatteryWidget()
}

private fun readBatteryLevel(context: Context): Int {
    val filter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
    val intent = context.registerReceiver(null, filter)
    return intent?.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) ?: -1
}

@Composable
private fun BatteryContent(level: Int) {
    Box(modifier = GlanceModifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "$level%")
    }
}
