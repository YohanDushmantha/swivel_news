package com.swivel.security.auth_manager

import android.content.Context
import com.swivel.config.constants.AppStates
import com.swivel.crypto.keystore_manager.KEYSTORE_ALIAS
import com.swivel.models.entities.UserAuthentication
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
     * store user authentication details into shared preferences
     *
     * @param userAuthentication user authentication object to insert
     * @return status of data insertion process, if user object is inserted successfully returns
     * true otherwise returns false
     */
    override fun storeUserAuthentication(
        userAuthentication : UserAuthentication?
    ) : Boolean?{

        //insert data into shared preferences
        return sharedPrefManger.pushData(
            context,
            SHARED_PREF_STORAGE_KEY.SESSION_DATA_KEY,
            KEYSTORE_ALIAS.DEFAULT,
            AuthManagerSharedKeys.USER_AUTHENTICATION.key,
            userAuthentication
        )?.let {
            if(it){
                // set driver authentication obejct into app states
                appState.userAuth = userAuthentication
                return@let  true
            }
            appState.userAuth = null
            false
        } ?: throw AuthManagerException("User authentication object storing process is failed.")
    }

    /**
     * fetch user authentication object
     * first, trying to get user authentication object from appState if it returns null, trying to
     * get user authentication object from shared preferences
     *
     * @return UserAuthentication object or null
     */
    override fun fetchUserAuthentication() : UserAuthentication?{
        // return user auth object inside app state object when it is not null
        appState.userAuth?.let {
            return it
        }

        // if user auth object inside app state object is null should fetch data from shared
        // preferences and return
        return sharedPrefManger.fetchData(
            context,
            SHARED_PREF_STORAGE_KEY.SESSION_DATA_KEY,
            KEYSTORE_ALIAS.DEFAULT,
            AuthManagerSharedKeys.USER_AUTHENTICATION.key,
            UserAuthentication::class.java
        )
    }

    /**
     * renew session id inside user authentication object
     * @param sessionId new session id
     * @return if renewal process is success returns true otherwise returns false
     */
    override fun renewSession(sessionId: String) : Boolean{
        return fetchUserAuthentication()?.let {
            val updatedUserAuth = it.apply {
                sessionID = sessionId
            }

            storeUserAuthentication(
                updatedUserAuth
            )?.let { result ->
                if(result){
                    // set user auth object inside app state object to updated user auth object
                    appState.userAuth = updatedUserAuth
                }
                result
            } ?: throw AuthManagerException("data insertion process is failed.")
        } ?: throw AuthManagerException("Unable to renew session with empty object of user authentication")
    }

    /**
     * provide capability to check session validity
     *
     * @return if session is valid return true otherwise false
     */
    override fun isActiveSession() : Boolean{
        return fetchUserAuthentication()?.let {
            it.loginStatus?.let {status ->
                status
            } ?: throw AuthManagerException("Session status checking process is failed due to login status not found")
        } ?: false
    }

    /**
     * return session id
     */
    override fun getSessionId() : String?{
        return fetchUserAuthentication()?.let {
            it.sessionID
        }
    }

    /**
     * provide capability to expire session, this method set session id attribute to null inside user
     * authentication object , not removing User Authentication object
     */
    override fun expireSession() : Boolean?{
        return fetchUserAuthentication()?.let {
            val updateDriverAuth = it.apply {
                sessionID = null
                loginStatus = false
            }

            // expire session id inside user auth obejct inside shared preferences
            storeUserAuthentication(
              updateDriverAuth
            )?.let{ result ->

                if(result){
                    // expire session id inside user auth object inside app states object
                    appState.userAuth = updateDriverAuth
                }
                result

            } ?: throw AuthManagerException("data insertion process is failed.")
        } ?: true
    }

    /**
     * invalidate user authentication object, this function removes user authentication object
     * from shared preferences
     */
    override fun invalidateUserAuthData() : Boolean{
        return storeUserAuthentication(null)?.let {
            if(it){
                appState.userAuth = null
            }
            it
        } ?: throw AuthManagerException("User auth data invalidation process is failed")
    }
}