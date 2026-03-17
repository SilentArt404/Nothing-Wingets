package com.nothingwingets.widgets.batterystatus.data

data class BatteryStatusState(
    val batteryPercent: Int = 87,
    val isCharging: Boolean = false,
    val wifiEnabled: Boolean = true,
    val bluetoothEnabled: Boolean = false
)

fun BatteryStatusState.toUiSummary(): String = toString()
