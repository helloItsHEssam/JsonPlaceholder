package com.iamhessam.jsonplaceholder.data.local.db.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.iamhessam.jsonplaceholder.data.local.db.room.dao.CommentDao
import com.iamhessam.jsonplaceholder.data.local.db.room.entity.CommentEntity

@Database(entities = [CommentEntity::class], version = 1, exportSchema = false)
abstract class AppDB: RoomDatabase() {

    abstract fun commentDao(): CommentDao

    companion object {
        private const val dbName: String = "db"
        private var instance_: AppDB? = null
        fun getInstance(context: Context): AppDB {
            if (instance_ == null) {
                instance_ = createInstance(context)
            }
            return instance_!!
        }

        @Synchronized
        private fun createInstance(context: Context): AppDB {
            return Room.databaseBuilder(context, AppDB::class.java, dbName).build()
        }
    }
}