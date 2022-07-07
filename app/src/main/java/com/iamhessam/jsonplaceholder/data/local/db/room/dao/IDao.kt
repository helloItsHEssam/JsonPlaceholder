package com.iamhessam.jsonplaceholder.data.local.db.room.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import com.iamhessam.jsonplaceholder.data.local.db.room.entity.IEntity

interface IDao<T : IEntity> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(entity: T): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(entities: List<T>): Array<Long>

    @Update
    suspend fun update(entity: T)

    @Update
    suspend fun update(entities: List<T>)

    @Delete
    suspend fun delete(entity: T)

    @Delete
    suspend fun delete(entities: List<T>)
}