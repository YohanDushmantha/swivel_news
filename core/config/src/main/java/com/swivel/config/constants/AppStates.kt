package com.swivel.config.constants

import com.swivel.models.entities.UserAuthentication
import com.swivel.models.entities.SystemConfiguration
import com.swivel.models.libs.utility.DeviceInfo

/**
 * @author Yohan Dushmantha
 * @class AppStates
 *
 * consists of app related status
 * Don't keep sensitive data inside app states
 */
class AppStates {
    var deviceInfo : DeviceInfo? = null
    //var sessionInfo : SessionInfo? = SessionInfo(null,null)
    var userAuth : UserAuthentication? = null
    var systemConfiguration : SystemConfiguration? = null
}