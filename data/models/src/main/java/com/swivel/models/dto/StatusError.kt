package com.swivel.models.dto

/**
 * @author Yohan Dushmantha
 * @class StatusError
 * contains error data which is return by server
 */
class StatusError {
    var statusMessage: String? = null
    var errorType: Int = 0
    var errorMessage: String? = null
}