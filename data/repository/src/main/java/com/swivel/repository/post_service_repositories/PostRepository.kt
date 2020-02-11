package com.swivel.repository.post_service_repositories

import com.swivel.local.dao.PostDao
import com.swivel.models.base.DataSource
import com.swivel.models.entities.Post
import com.swivel.remote.post_service.api.post_api.PostRemoteApi
import com.swivel.repository.core.BaseRepository
import com.swivel.repository.exception.DataSourceNotHandledException
import timber.log.Timber
import javax.inject.Inject

/**
 * @author Yohan Dushmantha
 * class PostRepository
 *
 * this is a sample repository class to handle sample post api. developer should create this kind of
 * repository classes to access datasources
 *
 * @param postRemoteApi remote api interface for handling network calls
 * @param postDao local db api interface for handling local db related calls
 *
 */
class PostRepository @Inject constructor(
    private val postRemoteApi: PostRemoteApi,
    private val postDao: PostDao
) : BaseRepository(){

    /**
     * fetch all posts inside the database all repository methods should be defined as suspend
     * classes
     *
     * @param dataSource relevant datasource which is needed to fetch data. developer can add
     * new datasource for the application. if developer add new datasource he should add that
     * datasource as an enum into DataSource Object
     * @return MutableList<Post>? this is optional list of post objects if this object got as an
     * empty object, there should be something wrong or empty records from db side
     */
    suspend fun fetchPosts(dataSource: DataSource? = DataSource.REMOTE) : MutableList<Post>?{
        Timber.i("YD -> PostRepository -> fetchPosts")
        when(dataSource){

            // handle remote datasource related data reqeusts
            DataSource.REMOTE -> {
                Timber.i("YD -> PostRepository -> fetchPosts REMOTE")
                return safeApiCall(
                    call = {postRemoteApi.fetchPostsAsync().await()},
                    errorMessage = "Error Fetching Posts"
                )?.toMutableList()
            }

            // handle local datasource related data requests
            DataSource.LOCAL -> {
                Timber.i("YD -> PostRepository -> fetchPosts LOCAL")
                return postDao.getAllPosts()
            }
            else -> throw DataSourceNotHandledException()
        }

    }

    /**
     * insert post into datasources
     *
     * @param dataSource relevant datasource which is needed to insert data.
     */
    suspend fun insertPost(dataSource: DataSource? = DataSource.REMOTE) {
        when (dataSource){
            DataSource.REMOTE -> {}
            DataSource.LOCAL -> {}
        }
    }
}