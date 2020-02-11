package com.swivel.models.features.login.verify_password.router_arguments

import com.swivel.models.features.IBaseDeepLinkArguments

/**
 * @author Yohan Dushmantha
 * @class VerifyPasswordDeepLinkArguments
 *
 * deepLink object for parsing data through deep link router
 */
class VerifyPasswordDeepLinkArguments constructor(
    var userMobileNumber : String? = null
) : IBaseDeepLinkArguments()