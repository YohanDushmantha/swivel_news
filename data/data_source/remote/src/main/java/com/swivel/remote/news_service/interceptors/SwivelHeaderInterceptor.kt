package com.swivel.remote.news_service.interceptors

import com.swivel.models.base.RequestHeaders
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 * @author Yohan Dushmantha
 * @interface NewsHeaderInterceptor
 *
 * interceptor for handling headers of Swivel service
 */
class SwivelHeaderInterceptor @Inject constructor(
    private var headers: RequestHeaders
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()

        builder.addHeader(headers.contentTypeKey,headers.contentTypeValue)
        builder.addHeader(headers.connectionTypeKey,headers.connectionTypeValue)
        builder.addHeader(headers.encodingTypeKey,headers.encodingTypeValue)

        return chain.proceed(builder.build())
    }
}