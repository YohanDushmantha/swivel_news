package com.swivel.security.auth_manager.exceptions

/**
 * @author Yohan Dushmantha
 * @class AuthManagerException
 *
 * this exception should be thrown when there is something wrong with AuthManager
 */
class AuthManagerException constructor(text : String? = null) : Exception() {
    override val message: String = text ?: "Exception in Auth Manager"
}