package com.swivel.utility.release_manager

/**
 * @author Yohan Dushmantha
 * @class IReleaseManager
 *
 * provide interface for ReleaseManager
 */
interface IReleaseManager {

    /**
     * check whether current release is production release build
     * if release is production release should return true else false
     * @return status whether production release or not
     */
    fun isProductionRelease() : Boolean

    /**
     * check whether current release is debug release or not
     * @return status whether debug release or not
     */
    fun isDebugRelease() : Boolean
}