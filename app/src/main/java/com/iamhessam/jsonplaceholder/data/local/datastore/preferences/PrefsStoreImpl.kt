package com.iamhessam.jsonplaceholder.data.local.datastore.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class PrefsStoreImpl @Inject constructor(private val dataStore: DataStore<Preferences>) :
    PrefsStore {

    override suspend fun <T> getPreference(key: Preferences.Key<T>, defaultValue: T): Flow<T> {
        return this.dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            val result = preferences[key] ?: defaultValue
            result
        }
    }

    override suspend fun <T> putPreference(key: Preferences.Key<T>, value: T) {
        this.dataStore.edit { preferences ->
            preferences[key] = value
        }
    }

    override suspend fun <T> removePreference(key: Preferences.Key<T>) {
        this.dataStore.edit {
            it.remove(key)
        }
    }

    override suspend fun clearAllPreference() {
        this.dataStore.edit {
            it.clear()
        }
    }
}