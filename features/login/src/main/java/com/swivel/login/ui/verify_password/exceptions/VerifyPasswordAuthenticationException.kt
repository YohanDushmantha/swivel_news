package com.swivel.login.ui.verify_password.exceptions

class VerifyPasswordAuthenticationException constructor(error : String? = null) : Exception(){
    override val message: String? = error ?: "Authentication Exception inside Verify Password"
}