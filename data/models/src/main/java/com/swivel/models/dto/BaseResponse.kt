package com.swivel.models.dto

import java.util.ArrayList

/**
 * @author Yohan Dushmantha
 * @class BaseResponse
 * super class of every response of backend services. this should be extended by every response
 * classes which is return from swivel backend services
 */
open class BaseResponse {
    var status: String? = null
    var totalResults: Int? = 0

    override fun toString(): String {
        return "BaseResponse(status=$status, totalResults=$totalResults)"
    }
}