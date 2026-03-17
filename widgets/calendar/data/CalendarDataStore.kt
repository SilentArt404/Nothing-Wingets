package com.nothingwingets.widgets.calendar.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val DATASTORE_NAME = "calendar_widget_prefs"
private val Context.calendarDataStore by preferencesDataStore(name = DATASTORE_NAME)
private val STATE_KEY = stringPreferencesKey("calendar_state")

class CalendarDataStore(private val context: Context) {
    val state: Flow<String> = context.calendarDataStore.data.map { prefs ->
        prefs[STATE_KEY] ?: ""
    }

    suspend fun saveRawState(rawState: String) {
        context.calendarDataStore.edit { prefs ->
            prefs[STATE_KEY] = rawState
        }
    }
}
