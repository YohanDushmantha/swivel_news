package com.swivel.remote.post_service.interceptors

import okhttp3.Interceptor
import okhttp3.Response

/**
 * @author Yohan Dushmantha
 * @interface PostAuthInterceptor
 *
 * interceptor for post remote api to handle request
 */
class PostAuthInterceptor : Interceptor{

    override fun intercept(chain: Interceptor.Chain): Response {
        /*val newUrl = chain.request().url
            .newBuilder()
            .addQueryParameter("api_key", AppConstants.tmdbApiKey)
            .build()

        val newRequest = chain.request()
            .newBuilder()
            .url(newUrl)
            .build()*/

        return chain.proceed(chain.request())
    }
}