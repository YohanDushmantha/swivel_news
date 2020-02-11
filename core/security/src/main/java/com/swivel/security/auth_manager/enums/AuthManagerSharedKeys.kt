package com.swivel.security.auth_manager.enums

/**
 * @author Yohan Dushmantha
 * @class SessionManagerSharedKeys
 *
 *
 */
enum class AuthManagerSharedKeys (val key : String) {
    Auth_ID("session_id"),
    USER_ID("user_id"),
    USER_AUTHENTICATION("user_authentication")
}