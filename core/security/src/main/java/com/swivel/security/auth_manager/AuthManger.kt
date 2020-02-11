package com.swivel.security.auth_manager

import android.content.Context
import com.swivel.config.constants.AppStates
import com.swivel.crypto.keystore_manager.KEYSTORE_ALIAS
import com.swivel.models.entities.DriverAuthentication
import com.swivel.security.auth_manager.enums.AuthManagerSharedKeys
import com.swivel.security.auth_manager.exceptions.AuthManagerException
import com.swivel.models.libs.shared_pref.enums.SHARED_PREF_STORAGE_KEY
import com.swivel.shared_pref.SharedPrefManager
import javax.inject.Inject

/**
 * @author Yohan Dushmantha
 * @class AuthManger
 *
 * Manage user sessions and validate session status
 */
class AuthManger @Inject constructor(
    private val sharedPrefManger : SharedPrefManager,
    private val context: Context,
    private val appState : AppStates
) : IAuthManager{

    /**
     * store driver authentication details into shared preferences
     *
     * @param driverAuthentication driver authentication object to insert
     * @return status of data insertion process, if driver object is inserted successfully returns
     * true otherwise returns false
     */
    override fun storeDriverAuthentication(
        driverAuthentication : DriverAuthentication?
    ) : Boolean?{

        //insert data into shared preferences
        return sharedPrefManger.pushData(
            context,
            SHARED_PREF_STORAGE_KEY.SESSION_DATA_KEY,
            KEYSTORE_ALIAS.DEFAULT,
            AuthManagerSharedKeys.DRIVER_AUTHENTICATION.key,
            driverAuthentication
        )?.let {
            if(it){
                // set driver authentication obejct into app states
                appState.driverAuth = driverAuthentication
                return@let  true
            }
            appState.driverAuth = null
            false
        } ?: throw AuthManagerException("Driver authentication object storing process is failed.")
    }

    /**
     * fetch driver authentication object
     * first, trying to get driver authentication object from appState if it returns null, trying to
     * get driver authentication object from shared preferences
     *
     * @return DriverAuthentication object or null
     */
    override fun fetchDriverAuthentication() : DriverAuthentication?{
        // return driver auth object inside app state object when it is not null
        appState.driverAuth?.let {
            return it
        }

        // if driver auth object inside app state object is null should fetch data from shared
        // preferences and return
        return sharedPrefManger.fetchData(
            context,
            SHARED_PREF_STORAGE_KEY.SESSION_DATA_KEY,
            KEYSTORE_ALIAS.DEFAULT,
            AuthManagerSharedKeys.DRIVER_AUTHENTICATION.key,
            DriverAuthentication::class.java
        )
    }

    /**
     * renew session id inside driver authentication object
     * @param sessionId new session id
     * @return if renewal process is success returns true otherwise returns false
     */
    override fun renewSession(sessionId: String) : Boolean{
        return fetchDriverAuthentication()?.let {
            val updatedDriverAuth = it.apply {
                sessionID = sessionId
            }

            storeDriverAuthentication(
                updatedDriverAuth
            )?.let { result ->
                if(result){
                    // set drier auth object inside app state object to updated driver auth object
                    appState.driverAuth = updatedDriverAuth
                }
                result
            } ?: throw AuthManagerException("data insertion process is failed.")
        } ?: throw AuthManagerException("Unable to renew session with empty object of driver authentication")
    }

    /**
     * provide capability to check session validity
     *
     * @return if session is valid return true otherwise false
     */
    override fun isActiveSession() : Boolean{
        return fetchDriverAuthentication()?.let {
            it.loginStatus?.let {status ->
                status
            } ?: throw AuthManagerException("Session status checking process is failed due to login status not found")
        } ?: false
    }

    /**
     * return session id
     */
    override fun getSessionId() : String?{
        return fetchDriverAuthentication()?.let {
            it.sessionID
        }
    }

    /**
     * provide capability to expire session, this method set session id attribute to null inside driver
     * authentication object , not removing Driver Authentication object
     */
    override fun expireSession() : Boolean?{
        return fetchDriverAuthentication()?.let {
            val updateDriverAuth = it.apply {
                sessionID = null
                loginStatus = false
            }

            // expire session id inside driver auth obejct inside shared preferences
            storeDriverAuthentication(
              updateDriverAuth
            )?.let{ result ->

                if(result){
                    // expire session id inside driver auth object inside app staets object
                    appState.driverAuth = updateDriverAuth
                }
                result

            } ?: throw AuthManagerException("data insertion process is failed.")
        } ?: true
    }

    /**
     * invalidate driver authentication object, this function removes driver authentication object
     * from shared preferences
     */
    override fun invalidateDriverAuthData() : Boolean{
        return storeDriverAuthentication(null)?.let {
            if(it){
                appState.driverAuth = null
            }
            it
        } ?: throw AuthManagerException("Driver auth data invalidation process is failed")
    }
}