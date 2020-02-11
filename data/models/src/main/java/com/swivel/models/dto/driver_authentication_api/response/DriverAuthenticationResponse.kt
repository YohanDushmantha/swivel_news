package com.swivel.models.dto.driver_authentication_api.response

import com.swivel.models.dto.BaseResponse
import com.swivel.models.entities.DriverAuthentication

/**
 * @author Yohan Dushmantha
 * @class MobileNumberVerificationResponse
 * response object for authenticate driver
 */
class DriverAuthenticationResponse : BaseResponse() {
    var result : DriverAuthentication? = null
}