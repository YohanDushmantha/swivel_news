package com.swivel.utility.network_connection_manager

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.os.AsyncTask
import android.os.Build
import androidx.annotation.RequiresApi
import timber.log.Timber
import java.io.IOException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.*
import javax.inject.Inject


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class NetworkConnectionManagerL @Inject constructor(
    private val connectivityManager: ConnectivityManager,
    private val networkRequest: NetworkRequest
) : INetworkConnectionManager{

    private var timer : Timer? = null
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
            checkInternetConnectionAvailability()
        }

        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            Timber.i("YC -> NC -> onAvailable ->")
            checkInternetConnectionAvailability()
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

    private fun checkInternetConnectionAvailability(delay: Long = 5){
            timer?.cancel()
            checkInternetCapability(delay)
            Timber.i("YC -> NC -> NET -> checkInternetConnectionAvailability below M -> request to check")
    }

    private fun checkInternetCapability(delay: Long){

        Timber.i("YC -> NC -> NET -> checkInternetCapability_M")
        timer = Timer()
        timer?.schedule(InternetCapabilityCheckingTimerTask(null,internetCapabilityCallback),delay*1000)
    }

    private val internetCapabilityCallback : (network : Network?) -> Unit = {
        AsyncTaskRunner(hasInternet,noInternet).execute()
    }

    private class AsyncTaskRunner constructor(
        private val hasInternetCallback : () -> Unit,
        private val noInternetCallback : () -> Unit
    ) : AsyncTask<String,String,String>(){

        override fun doInBackground(vararg params: String?): String {
            try {
                Timber.i("YC -> NC -> NET -> DO IN BACKGROUND")
                val url = URL("http://www.google.com")
                val urlc: HttpURLConnection = url.openConnection() as HttpURLConnection
                urlc.setConnectTimeout(4000)
                urlc.connect()
                if (urlc.getResponseCode() === 200) {
                    hasInternetCallback()
                }
            } catch (e1: MalformedURLException) {
                e1.printStackTrace()
                noInternetCallback()
            } catch (e: IOException) {
                e.printStackTrace()
                noInternetCallback()
            }finally {
                return ""
            }
        }
    }

    private val hasInternet : () -> Unit = {
        isInternetAlive = true
        internetChangedCallback?.let {
            it(isInternetAlive)
        }
        Timber.i("YC -> NC -> NET -> has internet inside")
    }

    private val noInternet : () -> Unit = {
        isInternetAlive = false
        internetChangedCallback?.let {
            it(isInternetAlive)
        }
        Timber.i("YC -> NC -> NET -> no internet inside M")
        checkInternetCapability(30)
    }

    override fun isInternetAccessible(): Boolean {
        return isInternetAlive
    }

    override fun requestToCheckInternetAccessibility() {
        checkInternetConnectionAvailability()
    }
}