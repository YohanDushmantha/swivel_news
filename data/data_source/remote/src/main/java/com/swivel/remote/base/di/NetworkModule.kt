package com.swivel.remote.base.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.swivel.models.base.RequestHeaders
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * @author Yohan Dushmantha
 * @class NetworkModule
 *
 * provides all kind of network related dependencies for dagger
 */
@Module
class NetworkModule {

    /**---------------------------------------------------------------------------------------------*
     * COMMON DEPENDENCY PROVIDERS - START
     *----------------------------------------------------------------------------------------------*/

    /**
     * provide OKHttpClientBuilder for dagger
     */
    @Singleton
    @Provides
    fun provideOKHttpClientBuilder(
        requestHeaders: RequestHeaders
    ) : OkHttpClient.Builder{
        return OkHttpClient().newBuilder().apply {
            readTimeout(requestHeaders.readTimeOut, TimeUnit.SECONDS)
            connectTimeout(requestHeaders.connectTimeOut, TimeUnit.SECONDS)
            writeTimeout(requestHeaders.writeTimeOut, TimeUnit.SECONDS)
        }
    }

    /**
     * provide gson builder for dagger
     */
    @Singleton
    @Provides
    fun provideGson() : Gson{
        return GsonBuilder().apply {
            //setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
        }.create()
    }


    /**
     * provide retrofit builder for dagger
     */
    @Singleton
    @Provides
    fun provideRetrofitBuilder(gson: Gson) : Retrofit.Builder {
        return Retrofit.Builder()
            //.addConverterFactory(MoshiConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
    }

    /**
     * provide logging interceptor for logging network calls
     */
    @Provides
    fun provideHttpLoggingInterceptor() : HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    /**---------------------------------------------------------------------------------------------*
     * COMMON DEPENDENCY PROVIDERS - END
     *----------------------------------------------------------------------------------------------*/


}