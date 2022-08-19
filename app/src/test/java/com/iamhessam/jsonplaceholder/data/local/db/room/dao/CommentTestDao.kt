package com.iamhessam.jsonplaceholder.data.local.db.room.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.iamhessam.jsonplaceholder.data.local.db.room.AppDB
import com.iamhessam.jsonplaceholder.data.local.db.room.entity.CommentEntity
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class CommentTestDao {

    private lateinit var db: AppDB
    private lateinit var commentDao: CommentDao

    @Before
    fun setup() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDB::class.java
        )
            .allowMainThreadQueries()
            .build()
        commentDao = db.commentDao()
    }

    @After
    fun teardown() {
        db.close()
    }

    @Test
    fun insertTest() {
        val commentEntity = CommentEntity(1,"hello")
        runBlocking {
            val id = commentDao.save(commentEntity)
            Assert.assertEquals(1, id)
        }
    }

    @Test
    fun updateTest() {
        val commentEntity = CommentEntity(1,"hello")
        runBlocking {
            commentDao.save(commentEntity)
            commentEntity.comment = "hello2"
            commentDao.update(commentEntity)

            val findEn = commentDao.findById(1)
            Assert.assertEquals("hello2", findEn?.comment)
        }
    }
}