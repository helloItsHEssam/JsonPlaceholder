package com.iamhessam.jsonplaceholder.data.local.datastore.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PrefsStoreImpl @Inject constructor(private val dataStore: DataStore<Preferences>) :
    PrefsStore {

    private object PrefsKey {
        val UI_Mode_Key = booleanPreferencesKey("ui_mode")
    }

    override suspend fun isNightMode(): Flow<Boolean> {
        return dataStore.data.map { prefs ->
            val key = prefs[PrefsKey.UI_Mode_Key] ?: false
            key
        }
    }

    override suspend fun updateDayMode() {
        this.dataStore.edit { prefs ->
            prefs[PrefsKey.UI_Mode_Key] = true
        }
    }

    override suspend fun updateNightMode() {
        this.dataStore.edit { prefs ->
            prefs[PrefsKey.UI_Mode_Key] = false
        }
    }
}