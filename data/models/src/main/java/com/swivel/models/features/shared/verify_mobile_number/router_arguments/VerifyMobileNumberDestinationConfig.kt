package com.swivel.models.features.shared.verify_mobile_number.router_arguments

import com.swivel.models.libs.navigation.enums.DEEP_LINK


/**
 * @author Yohan Dushmantha
 * @class VerifyMobileNumberDestinationConfig
 *
 * behave as a container for sending configuration data of Verify Mobile Number destinations.
 */
data class VerifyMobileNumberDestinationConfig constructor(
    var verifyMobileNumberOrigin : DEEP_LINK,
    var alreadyExistsUserDestination : DEEP_LINK? = null,
    var newUserDestination : DEEP_LINK? = null,
    var title : String? = null,
    var submitButtonText : String? = null
)