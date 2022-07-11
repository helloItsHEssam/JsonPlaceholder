package com.iamhessam.jsonplaceholder.data.local

import com.iamhessam.jsonplaceholder.data.local.db.room.AppDB
import javax.inject.Inject

class LocalRepositoryImpl : LocalRepository {

    @Inject override lateinit var appDB: AppDB
}