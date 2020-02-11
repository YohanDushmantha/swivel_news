package com.swivel.utility.release_manager

import com.swivel.models.base.AppConfig
import javax.inject.Inject

/**
 * @author Yohan Dushmantha
 * @class ReleaseManager
 *
 * provide capability for providing and handling release related information
 */
class ReleaseManager @Inject constructor(
    private val appConfig: AppConfig
) : IReleaseManager {

    override fun isProductionRelease(): Boolean {
        if(appConfig.flavor.equals("live") && !isDebugRelease()){
            return true
        }
        return false
    }

    override fun isDebugRelease(): Boolean {
        return appConfig.debug!!
    }

}