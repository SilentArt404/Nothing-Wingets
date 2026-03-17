package com.nothingwingets.widgets.core

import android.appwidget.AppWidgetManager
import android.os.Bundle

enum class WidgetSize {
    SMALL,
    MEDIUM,
    LARGE
}

/**
 * Maps launcher-provided span options to the closest widget size.
 * Provides graceful degradation on launchers with non-standard grid sizes.
 */
fun resolveWidgetSize(options: Bundle?): WidgetSize {
    if (options == null) return WidgetSize.MEDIUM

    val minWidth = options.getInt(AppWidgetManager.OPTION_APPWIDGET_MIN_WIDTH, 0)
    val minHeight = options.getInt(AppWidgetManager.OPTION_APPWIDGET_MIN_HEIGHT, 0)

    return when {
        minWidth >= 220 && minHeight >= 220 -> WidgetSize.LARGE
        minWidth >= 180 || minHeight >= 110 -> WidgetSize.MEDIUM
        else -> WidgetSize.SMALL
    }
}
