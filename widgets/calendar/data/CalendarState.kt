package com.nothingwingets.widgets.calendar.data

data class CalendarState(
    val nextEventTitle: String = "Design review",
    val nextEventEpochMillis: Long = System.currentTimeMillis() + 3_600_000
)

fun CalendarState.toUiSummary(): String = toString()
