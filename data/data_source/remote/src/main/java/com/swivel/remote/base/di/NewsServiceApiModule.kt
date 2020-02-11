package com.swivel.remote.base.di

import com.swivel.models.base.AppConfig
import com.swivel.models.base.RequestHeaders
import com.swivel.remote.news_service.api.news_api.NewsRemoteApi
import com.swivel.remote.news_service.api.common_api.SwivelCommonApi
import com.swivel.remote.news_service.interceptors.SwivelCommonInterceptor
import com.swivel.remote.news_service.interceptors.SwivelHeaderInterceptor
import com.swivel.remote.news_service.interceptors.SwivelNewsInterceptor
import com.swivel.remote.post_service.api.post_api.PostRemoteApi
import com.swivel.remote.post_service.interceptors.PostAuthInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

/**
 * @author Yohan Dushmantha
 * @class NewsServiceApiModule
 *
 * provides all kind of news service remote api related dependencies for dagger
 */
@Module
class NewsServiceApiModule {

    /**
     * provide header interceptor for handling news service web requests
     */
    @Provides
    fun provideNewsHeaderInterceptor(requestHeaders: RequestHeaders) : SwivelHeaderInterceptor{
        return SwivelHeaderInterceptor(requestHeaders)
    }

    /**---------------------------------------------------------------------------------------------*
     * NEWS API - START
     *----------------------------------------------------------------------------------------------*/

    /**
     * provide swivel news remote api. this api services will handle news
     * related network calls
     * @param retrofitBuilder retrofit builder for build api
     * @param loggingInterceptor interceptor for logging headers
     * @param swivelHeaderInterceptor interceptor for injecting headers
     * @param swivelNewsInterceptor post interceptor for intercept request
     * @param okHttpClientBuilder httpClientBuilder for creating api
     * @param appConfig app config for getting environment related data
     * @return created NewsRemoteApi
     */
    @Provides
    fun provideNewsApi(
        retrofitBuilder: Retrofit.Builder,
        loggingInterceptor: HttpLoggingInterceptor,
        swivelHeaderInterceptor: SwivelHeaderInterceptor,
        swivelNewsInterceptor: SwivelNewsInterceptor,
        okHttpClientBuilder: OkHttpClient.Builder,
        appConfig: AppConfig
    ) : NewsRemoteApi {
        return retrofitBuilder
            .client(
                okHttpClientBuilder
                    .addInterceptor(loggingInterceptor)
                    .addInterceptor(swivelHeaderInterceptor)
                    .addInterceptor(swivelNewsInterceptor)
                    .build())
            .baseUrl(appConfig.newsServiceEndPoint!!)
            .build()
            .create(NewsRemoteApi::class.java)
    }

    /**
     * provide SwivelNewsInterceptor for dagger
     * @return SwivelNewsInterceptor
     */

    @Provides
    fun provideSwivelNewsInterceptor() : SwivelNewsInterceptor{
        return SwivelNewsInterceptor()
    }

    /**---------------------------------------------------------------------------------------------*
     * NEWS API - END
     *----------------------------------------------------------------------------------------------*/

    /**---------------------------------------------------------------------------------------------*
     * Swivel COMMON API - START
     *----------------------------------------------------------------------------------------------*/

    /**
     * provide swivel common remote api. this api services will handle common api
     * related network calls
     * @param retrofitBuilder retrofit builder for build api
     * @param loggingInterceptor interceptor for logging headers
     * @param swivelHeaderInterceptor interceptor for injecting headers
     * @param swivelCommonInterceptor common interceptor for intercept request
     * @param okHttpClientBuilder httpClientBuilder for creating api
     * @param appConfig app config for getting environment related data
     * @return created SwivelCommonApi
     */
    @Provides
    fun provideSwivelCommonApi(
        retrofitBuilder: Retrofit.Builder,
        loggingInterceptor: HttpLoggingInterceptor,
        swivelHeaderInterceptor: SwivelHeaderInterceptor,
        swivelCommonInterceptor: SwivelCommonInterceptor,
        okHttpClientBuilder: OkHttpClient.Builder,
        appConfig: AppConfig
    ) : SwivelCommonApi {
        return retrofitBuilder
            .client(
                okHttpClientBuilder
                    .addInterceptor(loggingInterceptor)
                    .addInterceptor(swivelHeaderInterceptor)
                    .addInterceptor(swivelCommonInterceptor)
                    .build())
            .baseUrl(appConfig.newsServiceEndPoint!!)
            .build()
            .create(SwivelCommonApi::class.java)
    }

    /**
     * provide SwivelCommonInterceptor for dagger
     * @return SwivelCommonInterceptor
     */

    @Provides
    fun provideSwivelCommonInterceptor() : SwivelCommonInterceptor{
        return SwivelCommonInterceptor()
    }

    /**---------------------------------------------------------------------------------------------*
     * Swivel COMMON API - START
     *----------------------------------------------------------------------------------------------*/

    /**---------------------------------------------------------------------------------------------*
     * POST API - START
     *----------------------------------------------------------------------------------------------*/

    /**
     * provide post remote api. this is a sample api for testing network calls
     * @param retrofitBuilder retrofit builder for build api
     * @param postAuthInterceptor post interceptor for intercept request
     * @param okHttpClientBuilder httpClientBuilder for creating api
     * @return postRemoteApi created postRemoteApi
     */
    @Provides
    fun providePostApi(retrofitBuilder: Retrofit.Builder, postAuthInterceptor: PostAuthInterceptor, okHttpClientBuilder: OkHttpClient.Builder) : PostRemoteApi {
        return retrofitBuilder
            .client(
                okHttpClientBuilder
                    .addInterceptor(postAuthInterceptor)
                    .build())
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .build()
            .create(PostRemoteApi::class.java)
    }

    /**
     * provide PostAuthInterceptor for dagger
     * @return PostAuthInterceptor
     */

    @Provides
    fun providePostAuthInterceptor() : PostAuthInterceptor{
        return PostAuthInterceptor()
    }

    /**---------------------------------------------------------------------------------------------*
     * POST API - END
     *----------------------------------------------------------------------------------------------*/
}