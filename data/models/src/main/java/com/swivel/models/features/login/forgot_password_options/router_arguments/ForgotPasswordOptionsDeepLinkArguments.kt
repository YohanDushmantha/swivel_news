package com.swivel.models.features.login.forgot_password_options.router_arguments

import com.swivel.models.features.IBaseDeepLinkArguments

/**
 * @author Yohan Dushmantha
 * @class ForgotPasswordOptionsDeepLinkArguments
 *
 * deepLink object for parsing data through deep link router
 */
data class ForgotPasswordOptionsDeepLinkArguments constructor(
    val sampleData : String
) : IBaseDeepLinkArguments()