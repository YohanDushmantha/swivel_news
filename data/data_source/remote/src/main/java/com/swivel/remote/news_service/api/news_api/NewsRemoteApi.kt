package com.swivel.remote.news_service.api.news_api

import com.swivel.models.dto.driver_authentication_api.request.DriverAuthenticationRequest
import com.swivel.models.dto.driver_authentication_api.request.MobileNumberVerificationRequest
import com.swivel.models.dto.driver_authentication_api.response.DriverAuthenticationResponse
import com.swivel.models.dto.driver_authentication_api.response.MobileNumberVerificationResponse
import com.swivel.models.dto.driver_authentication_api.response.NewsResponse
import com.swivel.models.entities.Source
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * @author Yohan Dushmantha
 * @interface NewsRemoteApi
 *
 * remote api class for handle news api
 */
interface NewsRemoteApi {

    /**
     * fetch head line news list
     * @param page requested page
     * @param pageSize default size of page
     * @return Deferred<Response<NewsResponse>> return received news list
     */
    @GET("top-headlines?sources=cnn,bbc-news,abc-news,abc-news-au,ary-news")
    fun fetchHeadLineNews(
        @Query("page") page : Int?,
        @Query("pageSize") pageSize : Int?
    ) : Deferred<Response<NewsResponse>>

    /**
     * get filtered news result from whole news list
     * @param q filtered text
     * @param page requested page
     * @param pageSize default size of page
     * @return Deferred<Response<NewsResponse>> return received news list
     */
    @GET("everything")
    fun fetchFilteredNews(
        @Query("q") query : String?,
        @Query("page") page : Int?,
        @Query("pageSize") pageSize : Int? = 10
    ) : Deferred<Response<NewsResponse>>

}