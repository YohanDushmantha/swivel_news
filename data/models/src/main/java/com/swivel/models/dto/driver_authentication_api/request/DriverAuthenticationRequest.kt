package com.swivel.models.dto.driver_authentication_api.request

/**
 * @author Yohan Dushmantha
 * @class MobileNumberVerificationRequest
 *
 * request object for authenticate driver
 */
data class DriverAuthenticationRequest constructor(
    var mobileNumber : String? = null,
    var password : String? = null,
    var mobileDeviceToken : String? = null,
    var deviceTypeID : Int? = null,
    var deviceSerialNumber : String? = null,
    var deviceModel : String? = null,
    var osVersion : Int? = null,
    var currentAppVersion : Int? = null
)