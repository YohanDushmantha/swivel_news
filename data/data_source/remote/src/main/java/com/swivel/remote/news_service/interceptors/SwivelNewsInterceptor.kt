package com.swivel.remote.news_service.interceptors

import okhttp3.Interceptor
import okhttp3.Response

/**
 * @author Yohan Dushmantha
 * @interface SwivelNewsInterceptor
 *
 * interceptor for Swivel news remote api to handle request
 */
class SwivelNewsInterceptor : Interceptor{

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(chain.request())
    }

}