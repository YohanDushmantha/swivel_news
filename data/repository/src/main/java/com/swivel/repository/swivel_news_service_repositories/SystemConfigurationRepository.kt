package com.swivel.repository.swivel_news_service_repositories

import com.swivel.models.base.DataSource
import com.swivel.models.dto.common_api.response.SystemConfigurationResponse
import com.swivel.remote.news_service.api.common_api.SwivelCommonApi
import com.swivel.repository.core.BaseRepository
import com.swivel.repository.exception.DataSourceNotHandledException
import javax.inject.Inject

/**
 * @author Yohan Dushmantha
 * class SystemConfigurationRepository
 *
 * @param swivelCommonApi remote api interface for handling network calls
 *
 */
class SystemConfigurationRepository @Inject constructor(
    private val swivelCommonApi: SwivelCommonApi
) : BaseRepository(){

    /**
     * fetch system configuration data from requested data source
     * @param systemConfigurationTypeId system configuration id
     * @param dataSource Data source
     * @param customErrorMessage this message will use as error message if occured any error
     */
    suspend fun fetchSystemConfiguration(
        systemConfigurationTypeId : Int,
        dataSource: DataSource? = DataSource.REMOTE,
        customErrorMessage : String
    ) : SystemConfigurationResponse?{
        when(dataSource){

            DataSource.REMOTE -> {
                return safeApiCall(
                    call = {swivelCommonApi.getSystemConfigurationByApplicationTypeId(systemConfigurationTypeId).await()},
                    errorMessage = customErrorMessage
                )
            }

            else -> throw DataSourceNotHandledException()

        }
    }

}