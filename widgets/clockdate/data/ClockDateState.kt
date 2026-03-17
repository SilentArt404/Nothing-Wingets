package com.nothingwingets.widgets.clockdate.data

data class ClockDateState(
    val epochMillis: Long = System.currentTimeMillis(),
    val use24Hour: Boolean = true
)

fun ClockDateState.toUiSummary(): String = toString()
