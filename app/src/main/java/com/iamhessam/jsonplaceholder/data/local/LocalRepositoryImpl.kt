package com.iamhessam.jsonplaceholder.data.local

import com.iamhessam.jsonplaceholder.data.local.datastore.preferences.PrefsStore
import com.iamhessam.jsonplaceholder.data.local.db.room.AppDB
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    override var appDB: AppDB,
    override var prefsStore: PrefsStore
) : LocalRepository