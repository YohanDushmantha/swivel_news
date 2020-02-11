package com.swivel.models.base

/**
 * @author Yohan Dushmantha
 * @class RequestHeaders
 * keep http header data
 */
class RequestHeaders {
    val contentTypeKey  = "Content-Type"
    val contentTypeValue  = "application/json"
    val connectionTypeKey = "ConnectionRequest"
    val connectionTypeValue = "keep-alive"
    val encodingTypeKey = "Accept-Enconding"
    val encodingTypeValue = "application/json"

    val readTimeOut : Long = 240
    val writeTimeOut : Long = 240
    val connectTimeOut : Long = 240
}