package com.iamhessam.jsonplaceholder.data.local.datastore.preferences

import kotlinx.coroutines.flow.Flow

interface PrefsStore {
    suspend fun isNightMode(): Flow<Boolean>
    suspend fun updateNightMode()
    suspend fun updateDayMode()
}