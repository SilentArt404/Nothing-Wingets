package com.nothing.wingets.settings

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "wingets_settings")

class WidgetSettingsStore(private val context: Context) {
    private val styleKey = stringPreferencesKey("widget_style")

    val style: Flow<String> = context.dataStore.data.map { prefs: Preferences ->
        prefs[styleKey] ?: "nothing"
    }

    suspend fun setStyle(value: String) {
        context.dataStore.edit { prefs ->
            prefs[styleKey] = value
        }
    }
}
