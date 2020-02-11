package com.swivel.remote.news_service.api.news_api

import com.swivel.models.dto.driver_authentication_api.request.DriverAuthenticationRequest
import com.swivel.models.dto.driver_authentication_api.request.MobileNumberVerificationRequest
import com.swivel.models.dto.driver_authentication_api.response.DriverAuthenticationResponse
import com.swivel.models.dto.driver_authentication_api.response.MobileNumberVerificationResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * @author Yohan Dushmantha
 * @interface DriverAuthenticationRemoteApi
 *
 * remote api class for handle driver authentication related api calls
 */
interface NewsRemoteApi {

    /**
     * verify driver mobile number whether provided mobile number already exists or not
     * @param verifyMobileNumberVerificationRequest
     * @return MobileNumberVerificationResponse
     */
    @POST("DriverAuthentication/VerifyDriverMobileNumber")
    fun verifyDriverMobileNumber(
        @Body verifyMobileNumberVerificationRequest: MobileNumberVerificationRequest
    ) : Deferred<Response<MobileNumberVerificationResponse>>

    /**
     * authenticate user with username and password
     * @param driverAuthenticationRequest
     * @return DriverAuthenticationResponse
     */
    @POST("/DriverAuthentication/DriverLoginAuthentication")
    fun authenticateUser(
        @Body driverAuthenticationRequest: DriverAuthenticationRequest
    ) : Deferred<Response<DriverAuthenticationResponse>>

}