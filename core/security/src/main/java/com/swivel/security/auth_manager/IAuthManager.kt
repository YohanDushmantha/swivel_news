package com.swivel.security.auth_manager

import com.swivel.models.entities.DriverAuthentication

interface IAuthManager {

    fun storeDriverAuthentication(
        driverAuthentication : DriverAuthentication?
    ) : Boolean?

    fun fetchDriverAuthentication() : DriverAuthentication?

    fun renewSession(sessionId: String) : Boolean

    fun isActiveSession() : Boolean

    fun getSessionId() : String?

    fun expireSession() : Boolean?

    fun invalidateDriverAuthData() : Boolean

    /*fun createOrRenewSession(context: Context?, sessionId : String, sharedPrefStorageKey: SHARED_PREF_STORAGE_KEY? = SHARED_PREF_STORAGE_KEY.SESSION_DATA_KEY) : Boolean?
    fun hasActiveSession(context: Context?, sharedPrefStorageKey: SHARED_PREF_STORAGE_KEY? = SHARED_PREF_STORAGE_KEY.SESSION_DATA_KEY) : Boolean
    fun invalidateSession(context: Context?, sharedPrefStorageKey: SHARED_PREF_STORAGE_KEY? = SHARED_PREF_STORAGE_KEY.SESSION_DATA_KEY) : Boolean?
    fun getSessionId(context: Context?, sharedPrefStorageKey: SHARED_PREF_STORAGE_KEY? = SHARED_PREF_STORAGE_KEY.SESSION_DATA_KEY) : String?
    fun getUserId(context: Context?, sharedPrefStorageKey: SHARED_PREF_STORAGE_KEY? = SHARED_PREF_STORAGE_KEY.SESSION_DATA_KEY) : String?*/
}