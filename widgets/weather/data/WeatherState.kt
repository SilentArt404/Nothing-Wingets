package com.nothingwingets.widgets.weather.data

data class WeatherState(
    val temperatureC: Int = 21,
    val condition: String = "Sunny"
)

fun WeatherState.toUiSummary(): String = toString()
