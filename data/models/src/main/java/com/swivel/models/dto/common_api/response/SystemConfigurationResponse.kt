package com.swivel.models.dto.common_api.response

import com.swivel.models.dto.BaseResponse

/**
 * @author Yohan Dushmantha
 * @class SystemConfigurationResponse
 * behave as a wrapper of SystemConfiguration object which is return by backend services
 */
class SystemConfigurationResponse : BaseResponse() {
    var result : String? = null
}