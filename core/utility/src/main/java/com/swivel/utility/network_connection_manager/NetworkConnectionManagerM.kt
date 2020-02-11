package com.swivel.utility.network_connection_manager

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import androidx.annotation.RequiresApi
import timber.log.Timber
import java.lang.IllegalStateException
import java.util.*
import javax.inject.Inject


/**
 * @author Yohan Dushmantha
 * @class NetworkConnectionManagerM
 *
 * provide capability for checking network connection availability for above api versions than Marshmallow
 */
@RequiresApi(Build.VERSION_CODES.M)
class NetworkConnectionManagerM @Inject constructor(
    private val connectivityManager: ConnectivityManager,
    private val networkRequest: NetworkRequest
) : INetworkConnectionManager{

    var timer : Timer? = null
    override var isInternetAlive: Boolean = false
    override var internetChangedCallback: ((isAlive: Boolean) -> Unit)? = null


    /**---------------------------------------------------------------------------------------------*
     * NETWORK CALLBACK - START
     *----------------------------------------------------------------------------------------------*/

    /**
     * define network callback variable for registering and unregistering
     */
    private val networkCallback = object : ConnectivityManager.NetworkCallback(){

        override fun onLost(network: Network) {
            super.onLost(network)
            Timber.i("YC -> NC -> onLost ->")
            checkInternetConnectionAvailability(network)
        }

        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            Timber.i("YC -> NC -> onAvailable ->")
            checkInternetConnectionAvailability(network)
        }
    }

    /**
     * register network callback for identifying changes of network capabilities
     */
    override fun registerNetworkConnectionManager(changedCallback : (isAlive : Boolean) -> Unit) {
        try {
            unregisterNetworkConnectionManager()
        }catch (ex : Exception){
            Timber.e(ex.message)
        }
        connectivityManager.registerNetworkCallback(networkRequest,networkCallback)
        this.internetChangedCallback = changedCallback
        requestToCheckInternetAccessibility()
    }

    /**
     * register network callback
     */
    override fun unregisterNetworkConnectionManager() {
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }

    /**---------------------------------------------------------------------------------------------*
     * NETWORK CALLBACK - START
     *----------------------------------------------------------------------------------------------*/

    /**
     * check whether network is metered or not
     * @return if network is metered return true else false
     */
    override fun isActiveNetworkMetered() : Boolean{
        return connectivityManager.isActiveNetworkMetered
    }

    private fun checkInternetConnectionAvailability(network: Network?, delay: Long = 5){
            network?.let {
                timer?.cancel()
                checkInternetCapability(network, delay)
                Timber.i("YC -> NC -> NET -> checkInternetConnectionAvailability below M -> request to check")
            } ?: noInternet(null)
    }

    private fun checkInternetCapability(network : Network, delay: Long){
        try {
            Timber.i("YC -> NC -> NET -> checkInternetCapability_M")
            timer = Timer()
            timer?.let {
                timer?.schedule(InternetCapabilityCheckingTimerTask(network,internetCapabilityCallback),delay*1000)
            }
        }catch (ex : IllegalStateException){
            Timber.e(ex)
        }finally {

        }
    }

    private val internetCapabilityCallback : (network : Network? ) -> Unit = {

        it?.let{network ->
            connectivityManager.getNetworkCapabilities(network)?.let { networkCapabilities ->
                if(networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                    && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
                ) {
                    hasInternet(network)
                }else{
                    noInternet(network)
                }
            } ?: noInternet(network)
        } ?: noInternet(null)
    }

    private fun hasInternet(network: Network){
        isInternetAlive = true
        internetChangedCallback?.let {
            it(isInternetAlive)
        }
        Timber.i("YC -> NC -> NET -> has internet inside")
    }

    private fun noInternet(network: Network?){
        isInternetAlive = false
        internetChangedCallback?.let {
            it(isInternetAlive)
        }
        Timber.i("YC -> NC -> NET -> no internet inside M")
        connectivityManager.activeNetwork?.let {
            checkInternetConnectionAvailability(it,30)
        }

    }

    override fun isInternetAccessible(): Boolean {
        return isInternetAlive
    }

    override fun requestToCheckInternetAccessibility() {
        connectivityManager.activeNetwork?.let {
            checkInternetConnectionAvailability(it)
        } ?: noInternet(null)
    }
}