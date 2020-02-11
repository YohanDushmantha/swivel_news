package com.swivel.repository.driver_service_repositories

import android.content.Context
import com.swivel.models.base.DataSource
import com.swivel.models.dto.driver_authentication_api.request.DriverAuthenticationRequest
import com.swivel.models.dto.driver_authentication_api.request.MobileNumberVerificationRequest
import com.swivel.models.dto.driver_authentication_api.response.DriverAuthenticationResponse
import com.swivel.models.dto.driver_authentication_api.response.MobileNumberVerificationResponse
import com.swivel.remote.news_service.api.news_api.NewsRemoteApi
import com.swivel.repository.core.BaseRepository
import com.swivel.repository.exception.DataSourceNotHandledException
import com.swivel.shared_pref.SharedPrefManager
import javax.inject.Inject

/**
 * @author Yohan Dushmantha
 * class DriverAuthenticationRepository
 */
class DriverAuthenticationRepository @Inject constructor(
    private val driverAuthenticationRemoteApi: NewsRemoteApi,
    private val sharedPrefManager: SharedPrefManager,
    private val context : Context
) : BaseRepository(){

    /**
     * verify driver mobile number with backend, this method verifies whether driver already exists
     * inside the system or new driver
     *
     * @param mobileNumberVerificationRequest mobile number verification request behave as a request
     * wrapper
     * @param dataSource Data source
     * @param customErrorMessage this message will use as error message when error occurred
     */
    suspend fun verifyDriverMobileNumber(
        mobileNumberVerificationRequest : MobileNumberVerificationRequest,
        dataSource: DataSource? = DataSource.REMOTE,
        customErrorMessage : String
    ) : MobileNumberVerificationResponse?{
        when(dataSource){
            DataSource.REMOTE -> {
                return safeApiCall(
                    {driverAuthenticationRemoteApi.verifyDriverMobileNumber(mobileNumberVerificationRequest).await()},
                    customErrorMessage
                )
            }

            else -> throw DataSourceNotHandledException()
        }
    }

    /**
     * authenticate user using provided username, password and other data, checks whether provided
     * authenticate user details are valid or not
     *
     * @param driverAuthenticationRequest request object for authenticating driver
     * @param dataSource
     * @param customErrorMessage this message will use as error message when error occurred
     */
    suspend fun authenticateUser(
        driverAuthenticationRequest: DriverAuthenticationRequest,
        dataSource: DataSource? = DataSource.REMOTE,
        customErrorMessage: String
    ) : DriverAuthenticationResponse?{
        when(dataSource){
            DataSource.REMOTE -> {
                return safeApiCall(
                    {driverAuthenticationRemoteApi.authenticateUser(driverAuthenticationRequest).await()},
                    customErrorMessage
                )
            }

            else -> throw DataSourceNotHandledException()
        }
    }

}