package com.swivel.shared_pref

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.Gson
import com.swivel.crypto.crypto_manager.CryptoManager
import com.swivel.crypto.keystore_manager.KEYSTORE_ALIAS
import com.swivel.models.libs.shared_pref.enums.SHARED_PREF_STORAGE_KEY
import javax.inject.Inject

/**
 * @author Yohan Dushmantha
 * @class SharedPrefManager
 *
 * shared preference manager for manage data
 */
class SharedPrefManager @Inject constructor(
    private val cryptoManager: CryptoManager,
    private val gson: Gson
){

    /**
     * get shared preferences object
     * @param context application context
     * @param storageKey shared preference storage key for storage
     *
     * @return SharedPreference object
     */
    fun getSharedPref(context: Context?, storageKey: SHARED_PREF_STORAGE_KEY) : SharedPreferences?{
        return context?.getSharedPreferences(
            storageKey.KEY,
            Context.MODE_PRIVATE
        )
    }

    /**
     * push data into shared preference storage
     *
     * @param context application context
     * @param storageKey key of shared preferences storage
     * @param keystoreAlias alias of keystore
     * @param dataKey key of data
     * @param dataValue value
     *
     * @return if data insertion process is successful returns true otherwise return false
     */
    fun pushData(
        context: Context?,
        storageKey: SHARED_PREF_STORAGE_KEY,
        keystoreAlias: KEYSTORE_ALIAS,
        dataKey : String,
        dataValue : String?
    ) : Boolean? {
        /*cryptoService.createMasterKey()*/
        getSharedPref(context,storageKey)?.edit {
            var encryptedData : String? = null
            dataValue?.let {
                encryptedData = cryptoManager.encrypt(it)
            }
            this.putString(dataKey, encryptedData)
            this.apply()
            return this.commit()
        }
        return false
    }

    /**
     * push data into shared preference storage
     *
     * @param context application context
     * @param storageKey key of shared preferences storage
     * @param keystoreAlias alias of keystore
     * @param dataKey key of data
     * @param dataValue value
     *
     * @return if data insertion process is successful returns true otherwise return false
     */
    fun pushData(
        context: Context?,
        storageKey: SHARED_PREF_STORAGE_KEY,
        keystoreAlias: KEYSTORE_ALIAS,
        dataKey : String,
        dataValue : Any?
    ) : Boolean? {
        /*cryptoService.createMasterKey()*/
        getSharedPref(context,storageKey)?.edit {
            var encryptedData : String? = null
            dataValue?.let { data ->
                gson.toJson(dataValue)?.let { json ->
                    encryptedData = cryptoManager.encrypt(json)
                }
            }
            this.putString(dataKey, encryptedData)
            this.apply()
            return this.commit()
        }
        return false
    }

    /**
     * fetch data from shared preference storage
     *
     * @param context application context
     * @param storageKey key of shared preferences storage
     * @param keystoreAlias alias of keystore
     * @param dataKey key of data
     *
     * @return if there is some data relevant to provided key returns value otherwise return null
     */
    fun fetchData(
        context: Context?,
        storageKey: SHARED_PREF_STORAGE_KEY,
        keystoreAlias: KEYSTORE_ALIAS,
        dataKey : String
    ) : String?{
        return getSharedPref(context,storageKey)?.getString(dataKey,null)?.let {
            cryptoManager.decrypt(it)
        }
    }

    /**
     * fetch data from shared preference storage
     *
     * @param context application context
     * @param storageKey key of shared preferences storage
     * @param keystoreAlias alias of keystore
     * @param dataKey key of data
     * @param dataType datatype for return value
     * @return if there is some data relevant to provided key returns value otherwise return null
     */
    fun <T : Any> fetchData(
        context: Context?,
        storageKey: SHARED_PREF_STORAGE_KEY,
        keystoreAlias: KEYSTORE_ALIAS,
        dataKey : String,
        dataType : Class<T>
    ) : T? {
        val returnValue = getSharedPref(context,storageKey)?.getString(dataKey,null)
        returnValue?.let {
            return gson.fromJson(cryptoManager.decrypt(returnValue),dataType)
        } ?: return null

    }

}