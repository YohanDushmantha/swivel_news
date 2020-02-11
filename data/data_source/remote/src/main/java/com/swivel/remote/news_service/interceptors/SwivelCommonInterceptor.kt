package com.swivel.remote.news_service.interceptors

import okhttp3.Interceptor
import okhttp3.Response

/**
 * @author Yohan Dushmantha
 * @interface SwivelCommonInterceptor
 *
 * interceptor for driver remote api to handle request
 */
class SwivelCommonInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(chain.request())
    }
}