package com.swivel.utility.network_connection_manager

import android.net.Network
import timber.log.Timber
import java.util.*

/**
 * @author Yohan Dushmantha
 * @class InternetCapabilityCheckingTimerTask
 *
 * Timer task for executing when notified network capability changes
 */
class InternetCapabilityCheckingTimerTask constructor(private val network: Network?, private val callback : (network : Network?) -> Unit) : TimerTask(){
    override fun run() {
        try {
            network?.let {
                callback(network)
            } ?: callback(null)

        }catch (ex : Exception){
            Timber.e(ex)
        }
        finally {
            cancel()
        }
    }
}