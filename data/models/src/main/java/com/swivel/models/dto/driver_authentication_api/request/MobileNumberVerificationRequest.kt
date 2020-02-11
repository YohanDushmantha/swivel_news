package com.swivel.models.dto.driver_authentication_api.request

/**
 * @author Yohan Dushmantha
 * @class MobileNumberVerificationRequest
 *
 * request object for verifying mobile number
 */
data class MobileNumberVerificationRequest constructor(
    val mobileNumber : String? = null
)