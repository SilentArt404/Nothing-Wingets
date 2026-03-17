package com.nothingwingets.widgets.quicktoggles.data

data class QuickTogglesState(
    val wifiEnabled: Boolean = true,
    val bluetoothEnabled: Boolean = false,
    val flashlightEnabled: Boolean = false
)

fun QuickTogglesState.toUiSummary(): String = toString()
