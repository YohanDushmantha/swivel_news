package com.swivel.repository.swivel_news_service_repositories.exceptions

/**
 * @author Yohan Dushmantha
 * @class UserAuthenticationException
 *
 * UserAuthenticationException should be throw when error occured during user registraton and authentication
 */
class UserAuthenticationException constructor(val errorMessage : String? = null) : Exception() {

    override val message: String?
        get() = errorMessage ?: "User Authentication Failed Exception"

}