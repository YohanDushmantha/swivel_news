package com.swivel.shared_pref.base.di

import com.google.gson.Gson
import com.swivel.crypto.crypto_manager.CryptoManager
import com.swivel.shared_pref.SharedPrefManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author Yohan Dushmantha
 * @class SharedPrefModule
 *
 * provides all kind of shared preferences related dependencies for dagger
 */
@Module
class SharedPrefModule {

    @Singleton
    @Provides
    fun getSharedPrefManager(cryptoManager: CryptoManager, gson: Gson) : SharedPrefManager{
        return SharedPrefManager(cryptoManager,gson)
    }

}