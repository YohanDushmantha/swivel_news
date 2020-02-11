package com.swivel.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.swivel.local.dao.PostDao
import com.swivel.models.entities.Post

/**
 * @author Yohan Dushmantha
 * @class BaseRoomDatabase
 *
 * Base Room Database class for the application.
 */
@Database(entities = arrayOf(Post::class), version = 1 , exportSchema = true)
abstract class BaseRoomDatabase : RoomDatabase(){

    abstract fun postDao() : PostDao

}