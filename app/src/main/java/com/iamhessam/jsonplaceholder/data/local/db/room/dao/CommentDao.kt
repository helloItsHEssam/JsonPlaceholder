package com.iamhessam.jsonplaceholder.data.local.db.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.iamhessam.jsonplaceholder.data.local.db.room.entity.CommentEntity

@Dao
interface CommentDao: IDao<CommentEntity> {

    @Query("SELECT * FROM comment where id = :id")
    suspend fun findById(id: Long): CommentEntity?

    @Query("SELECT * FROM comment")
    suspend fun findAll(): Array<CommentEntity>
}