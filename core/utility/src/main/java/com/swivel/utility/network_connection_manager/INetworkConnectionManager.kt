package com.swivel.utility.network_connection_manager

/**
 * @author Yohan Dushmantha
 * @class INetworkConnectionManager
 *
 * provide interface for NetworkConnectionManager
 */
interface INetworkConnectionManager {

    var isInternetAlive : Boolean
    var internetChangedCallback : ((isAlive: Boolean) -> Unit)?

    /**
     * register network connection manager for identifying changes of network capabilities
     */
    fun registerNetworkConnectionManager(internetChangedCallback : (isAlive : Boolean) -> Unit)

    /**
     * unregister network connection manager
     */
    fun unregisterNetworkConnectionManager()

    /**
     * check whether network is metered or not
     * @return if network is metered return true else false
     */
    fun isActiveNetworkMetered() : Boolean

    /**
     * check whether internet is in active status or not
     * @return if internet status is in active status return true else false
     */
    fun isInternetAccessible() : Boolean

    /**
     * request to check internet accessibility manually
     */
    fun requestToCheckInternetAccessibility()

}