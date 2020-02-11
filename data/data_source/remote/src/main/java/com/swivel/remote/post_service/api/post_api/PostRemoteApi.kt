package com.swivel.remote.post_service.api.post_api

import com.swivel.models.entities.Post
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

/**
 * @author Yohan Dushmantha
 * @interface PostRemoteApi
 *
 * remote api class for handle post related api calls
 */
interface PostRemoteApi {

    @GET("posts")
    fun fetchPostsAsync() : Deferred<Response<List<Post>>>
}