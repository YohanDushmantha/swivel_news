package com.swivel.models.entities

/**
 * @author Yohan Dushmantha
 * @class DriverAuthentication
 * contains of driver authentication details
 */
data class DriverAuthentication constructor(

    /**---------------------------------------------------------------------------------------------*
     * PROPERTIES WHICH IS RECEIVED FROM BACKEND - START
     *----------------------------------------------------------------------------------------------*/
    var driverID : Int? = null,
    var driverMobileDeviceID : String? = null,
    var loginStatus : Boolean? = null,
    var sessionID : String? = null,

    /**---------------------------------------------------------------------------------------------*
     * PROPERTIES WHICH IS RECEIVED FROM BACKEND - END
     *----------------------------------------------------------------------------------------------*/

    /**---------------------------------------------------------------------------------------------*
     * PROPERTIES WHICH IS ADDED BY FRONT END - START
     *----------------------------------------------------------------------------------------------*/

    var mobileNumber : String? = null

    /**---------------------------------------------------------------------------------------------*
     * PROPERTIES WHICH IS ADDED BY FRONT END - END
     *----------------------------------------------------------------------------------------------*/
)