package com.iamhessam.jsonplaceholder.data.local

import com.iamhessam.jsonplaceholder.data.local.datastore.preferences.PrefsStore
import com.iamhessam.jsonplaceholder.data.local.db.room.AppDB
import javax.inject.Inject

class LocalRepositoryImpl : LocalRepository {

    @Inject override lateinit var appDB: AppDB
    @Inject override lateinit var prefsStore: PrefsStore
}