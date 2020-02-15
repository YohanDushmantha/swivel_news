package com.swivel.remote.news_service.interceptors

import com.swivel.models.base.AppConfig
import com.swivel.models.base.RequestHeaders
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject


/**
 * @author Yohan Dushmantha
 * @interface SwivelNewsInterceptor
 *
 * interceptor for Swivel news remote api to handle request
 */
class SwivelNewsInterceptor @Inject constructor(
    private var headers: RequestHeaders,
    private var appConfig: AppConfig
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val requestBuilder: Request.Builder = original.newBuilder()

        appConfig.newsApiKey?.let {
                requestBuilder
                .header(headers.newsApiKey, it)
                .method(original.method, original.body)
        }



        return chain.proceed(requestBuilder.build())
    }

}