package com.swivel.models.libs.utility

/**
 * @author Yohan Dushmantha
 * @class DeviceInfo
 *
 * behave as a device info container
 */
data class DeviceInfo (
    var imei : String,
    var os : Int,
    var appVersion : String,
    var buildNumber : Int,
    var model : String?
)