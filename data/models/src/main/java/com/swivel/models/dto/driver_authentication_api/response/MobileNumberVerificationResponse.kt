package com.swivel.models.dto.driver_authentication_api.response

import com.swivel.models.dto.BaseResponse
import com.swivel.models.entities.MobileNumberVerification

/**
 * @author Yohan Dushmantha
 * @class MobileNumberVerificationResponse
 * behave as a wrapper of MobileNumberVerification object which is return by backend services
 */
class MobileNumberVerificationResponse : BaseResponse() {
    var result : MobileNumberVerification? = null
}