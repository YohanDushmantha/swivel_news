package com.swivel.models.base

import javax.inject.Inject

/**
 * @author Yohan Dushmantha
 * @class AppConstants
 * keep application related configurations
 */
class AppConstants {
    // getting system configuration
    val systemConfigurationId = 2

    // platform id - default android platform id is 2 , ios 1
    val platformId = 2

    @Inject lateinit var requestHeaders: RequestHeaders
}