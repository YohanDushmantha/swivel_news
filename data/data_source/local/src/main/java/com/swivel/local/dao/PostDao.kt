package com.swivel.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.swivel.models.entities.Post

/**
 * @author Yohan Dushmantha
 * @class PostDao
 *
 * PostDao class for managing post objects from datasource
 */
@Dao
interface PostDao {

    /**
     * get all posts object
     * @return MutableList<Post> mutableList of post objects
     */
    @Query("SELECT * FROM post_table ORDER BY post_id ASC")
    fun getAllPosts() : MutableList<Post>

    /**
     * insert post object to database
     * @param post post object
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(post : Post)

    /**
     * delete all post objects from database
     */
    @Query("DELETE FROM post_table")
    suspend fun deleteAll()

}