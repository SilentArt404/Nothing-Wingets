package com.nothingwingets.widgets.batterystatus.provider

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import com.nothingwingets.widgets.R
import com.nothingwingets.widgets.core.resolveWidgetSize

class BatteryStatusWidgetProvider : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray,
    ) {
        appWidgetIds.forEach { widgetId ->
            val size = resolveWidgetSize(appWidgetManager.getAppWidgetOptions(widgetId))
            val views = RemoteViews(context.packageName, R.layout.widget_placeholder).apply {
                setTextViewText(R.id.widget_title, context.getString(R.string.batterystatus_widget_title))
                setTextViewText(R.id.widget_subtitle, size.name)
            }
            appWidgetManager.updateAppWidget(widgetId, views)
        }
    }
}
