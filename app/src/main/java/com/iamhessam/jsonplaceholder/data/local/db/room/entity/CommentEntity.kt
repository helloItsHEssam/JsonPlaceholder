package com.iamhessam.jsonplaceholder.data.local.db.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comment")
data class CommentEntity(

    @PrimaryKey
    @ColumnInfo(name = "id")
    override var id: Long,

    @ColumnInfo(name = "comment")
    var comment: String

): IEntity