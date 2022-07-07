package com.iamhessam.jsonplaceholder.data.local.db.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.iamhessam.jsonplaceholder.data.local.db.room.dao.CommentDao
import com.iamhessam.jsonplaceholder.data.local.db.room.entity.CommentEntity

@Database(entities = [CommentEntity::class], version = 1, exportSchema = false)
abstract class AppDB: RoomDatabase() {

    abstract fun commentDao(): CommentDao
}