package com.swivel.models.entities

/**
 * @author Yohan Dushmantha
 * @class SystemConfiguration
 * contains of system configurations which is related to application type
 */
data class SystemConfiguration constructor(
    var PasswordPolicy : String? = null,
    var OTPTimeout : String? = null,
    var OTPResendAttemptCount : String? = null,
    var DriverLoginPasswordPolicyHint : String? = null,
    var PickupPinImageURL : String? = null,
    var IntermidiateLocationPinImageURL : String? = null,
    var DropPinImageURL : String? = null,
    var NavigationRefreshDistance : String? = null,
    var AWSAccessTokenTravelPartner : String? = null
)