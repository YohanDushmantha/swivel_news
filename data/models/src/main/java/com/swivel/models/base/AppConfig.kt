package com.swivel.models.base

/**
 * @author Yohan Dushmantha
 * @class AppConfig
 * keep application related configurations
 */
class AppConfig {
    var debug                   : Boolean? = null
    var applicationId           : String? = null
    var buildType               : String? = null
    var flavor                  : String? = null
    var versionCode             : Int? = null
    var versionName             : String? = null

    var newsServiceEndPoint   : String? = null
    var mapServiceEndPoint      : String? = null
    var notificationEndPoint    : String? = null
    var newsApiKey              : String? = null
    var s3Bucket                : String? = null
    var s3BucketEndPoint        : String? = null

}