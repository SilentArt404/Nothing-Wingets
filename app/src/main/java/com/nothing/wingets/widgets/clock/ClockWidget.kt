package com.nothing.wingets.widgets.clock

import android.content.Context
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
import androidx.glance.text.TextStyle
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class ClockWidget : GlanceAppWidget() {
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            ClockContent()
        }
    }
}

class ClockWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = ClockWidget()
}

@Composable
private fun ClockContent() {
    val now = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"))
    Box(modifier = GlanceModifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = now, style = TextStyle())
    }
}
