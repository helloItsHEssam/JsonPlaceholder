package com.iamhessam.jsonplaceholder.data.local

import com.iamhessam.jsonplaceholder.data.local.db.room.AppDB

class LocalRepository(appDB: AppDB) {
    fun sample() {

    }

    var appDB: AppDB = appDB
        private set
}