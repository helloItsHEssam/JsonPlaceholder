package com.iamhessam.jsonplaceholder.data.local

import com.iamhessam.jsonplaceholder.data.local.datastore.preferences.PrefsStore
import com.iamhessam.jsonplaceholder.data.local.db.room.AppDB

interface LocalRepository {
    val appDB: AppDB
    val prefsStore: PrefsStore
}