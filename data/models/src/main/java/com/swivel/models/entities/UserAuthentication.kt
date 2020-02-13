package com.swivel.models.entities

/**
 * @author Yohan Dushmantha
 * @class DriverAuthentication
 * contains of driver authentication details
 */
data class UserAuthentication constructor(
    var loginStatus : Boolean? = null,
    var sessionID : String? = null,
    var user : User? = null
)