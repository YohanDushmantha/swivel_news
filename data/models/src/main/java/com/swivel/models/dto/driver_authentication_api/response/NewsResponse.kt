package com.swivel.models.dto.driver_authentication_api.response

import com.swivel.models.dto.BaseResponse
import com.swivel.models.entities.News

/**
 * @author YohanDushmantha
 * @class NewsResponse
 *
 * wrapper class of news list
 */
class NewsResponse : BaseResponse(){
    var articles : ArrayList<News>? = null
}