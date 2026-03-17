package com.nothingwingets.widgets.clockdate.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val DATASTORE_NAME = "clockdate_widget_prefs"
private val Context.clockdateDataStore by preferencesDataStore(name = DATASTORE_NAME)
private val STATE_KEY = stringPreferencesKey("clockdate_state")

class ClockDateDataStore(private val context: Context) {
    val state: Flow<String> = context.clockdateDataStore.data.map { prefs ->
        prefs[STATE_KEY] ?: ""
    }

    suspend fun saveRawState(rawState: String) {
        context.clockdateDataStore.edit { prefs ->
            prefs[STATE_KEY] = rawState
        }
    }
}
