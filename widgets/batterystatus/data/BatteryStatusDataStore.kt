package com.nothingwingets.widgets.batterystatus.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val DATASTORE_NAME = "batterystatus_widget_prefs"
private val Context.batterystatusDataStore by preferencesDataStore(name = DATASTORE_NAME)
private val STATE_KEY = stringPreferencesKey("batterystatus_state")

class BatteryStatusDataStore(private val context: Context) {
    val state: Flow<String> = context.batterystatusDataStore.data.map { prefs ->
        prefs[STATE_KEY] ?: ""
    }

    suspend fun saveRawState(rawState: String) {
        context.batterystatusDataStore.edit { prefs ->
            prefs[STATE_KEY] = rawState
        }
    }
}
