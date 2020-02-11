package com.swivel.remote.news_service.api.common_api

import com.swivel.models.dto.common_api.response.SystemConfigurationResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Yohan Dushmantha
 * @interface SwivelCommonApi
 *
 * remote api class for handle common api calls
 */
interface SwivelCommonApi {

    @GET("SystemConfiguration/GetSystemConfigurationByConfigurationTypeID")
    fun getSystemConfigurationByApplicationTypeId(@Query("configurationTypeID") configurationTypeId : Int) : Deferred<Response<SystemConfigurationResponse>>
}