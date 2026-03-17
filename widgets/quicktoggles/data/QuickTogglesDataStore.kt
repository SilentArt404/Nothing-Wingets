package com.nothingwingets.widgets.quicktoggles.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val DATASTORE_NAME = "quicktoggles_widget_prefs"
private val Context.quicktogglesDataStore by preferencesDataStore(name = DATASTORE_NAME)
private val STATE_KEY = stringPreferencesKey("quicktoggles_state")

class QuickTogglesDataStore(private val context: Context) {
    val state: Flow<String> = context.quicktogglesDataStore.data.map { prefs ->
        prefs[STATE_KEY] ?: ""
    }

    suspend fun saveRawState(rawState: String) {
        context.quicktogglesDataStore.edit { prefs ->
            prefs[STATE_KEY] = rawState
        }
    }
}
