package com.iamhessam.jsonplaceholder.data.local

import android.content.Context
import androidx.room.Room
import com.iamhessam.jsonplaceholder.data.local.db.room.AppDB
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class LocalRepository @Inject constructor(@ApplicationContext private val context: Context) {

    lateinit var appDB: AppDB
        private set

    init {
        initRoomDB()
    }

    private fun initRoomDB() {
        appDB = Room
            .databaseBuilder(this.context, AppDB::class.java, "db")
            .build()
    }

}