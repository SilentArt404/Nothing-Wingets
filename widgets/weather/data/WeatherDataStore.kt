package com.nothingwingets.widgets.weather.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val DATASTORE_NAME = "weather_widget_prefs"
private val Context.weatherDataStore by preferencesDataStore(name = DATASTORE_NAME)
private val STATE_KEY = stringPreferencesKey("weather_state")

class WeatherDataStore(private val context: Context) {
    val state: Flow<String> = context.weatherDataStore.data.map { prefs ->
        prefs[STATE_KEY] ?: ""
    }

    suspend fun saveRawState(rawState: String) {
        context.weatherDataStore.edit { prefs ->
            prefs[STATE_KEY] = rawState
        }
    }
}
