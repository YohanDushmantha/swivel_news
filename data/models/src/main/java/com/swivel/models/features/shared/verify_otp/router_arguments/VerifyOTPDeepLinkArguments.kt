package com.swivel.models.features.shared.verify_otp.router_arguments

import com.swivel.models.features.IBaseDeepLinkArguments

/**
 * @author Yohan Dushmantha
 * @class VerifyOTPDeepLinkArguments
 *
 * deepLink object for parsing data through deep link router
 */
data class VerifyOTPDeepLinkArguments constructor(
    var userMobileNumber : String? = null
)  : IBaseDeepLinkArguments()