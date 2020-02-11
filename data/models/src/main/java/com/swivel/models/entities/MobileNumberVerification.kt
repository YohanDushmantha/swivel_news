package com.swivel.models.entities

/**
 * @author Yohan Dushmantha
 * @class MobileNumberVerification
 * contains of Mobile Number Verification details
 */
data class MobileNumberVerification constructor(
    val isUserExists : Boolean? = null,
    val otp : Otp? = null
)