package com.swivel.models.features.shared.verify_mobile_number.router_arguments

import com.swivel.models.features.IBaseDeepLinkArguments

/**
 * @author Yohan Dushmantha
 * @class VerifyMobileNumberDeepLinkArguments
 *
 * deepLink object for parsing data through deep link router
 */
data class VerifyMobileNumberDeepLinkArguments constructor(
    val verifyMobileNumberDestinationConfig: VerifyMobileNumberDestinationConfig
): IBaseDeepLinkArguments()