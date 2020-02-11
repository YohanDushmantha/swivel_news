package com.swivel.security.auth_manager

import com.swivel.models.entities.UserAuthentication

interface IAuthManager {

    fun storeUserAuthentication(
        userAuthentication : UserAuthentication?
    ) : Boolean?

    fun fetchUserAuthentication() : UserAuthentication?

    fun renewSession(sessionId: String) : Boolean

    fun isActiveSession() : Boolean

    fun getSessionId() : String?

    fun expireSession() : Boolean?

    fun invalidateUserAuthData() : Boolean
}