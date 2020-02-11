package com.swivel.models.dto

import java.util.ArrayList

/**
 * @author Yohan Dushmantha
 * @class BaseResponse
 * super class of every response of backend services. this should be extended by every response
 * classes which is return from swivel backend services
 */
open class BaseResponse {
    var statusCode: Int = 0
    var errorList: ArrayList<StatusError>? = null
    var responseStatusCode: String? = null

    override fun toString(): String {
        return "ResponseBase(statusCode=$statusCode, errorList=$errorList, responseStatusCode=$responseStatusCode)"
    }
}