package com.swivel.crypto.base.di

import android.content.Context
import com.swivel.crypto.crypto_manager.CryptoManager
import com.swivel.crypto.crypto_manager.KeyStoreWrapper
import com.swivel.crypto.keystore_manager.KeystoreManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author Yohan Dushmantha
 * @class CryptoModule
 *
 * provide dependencies for crypto related activities
 */
@Module
class CryptoModule {

    /*@Singleton
    @Provides
    fun provideCryptoManager(keyStoreManager: KeyStoreManager) : CryptoManager {
        return CryptoManager(keyStoreManager)
    }

    @Singleton
    @Provides
    fun provideKeyStoreManager() : KeyStoreManager {
        return KeyStoreManager()
    }*/

/*    @Singleton
    @Provides
    fun provideCipherWrapper() : CipherWrapper{
        return CipherWrapper()
    }*/

    @Singleton
    @Provides
    fun provideKeystoreManager(context: Context, keyStoreWrapper: KeyStoreWrapper) : KeystoreManager{
        return KeystoreManager(context,keyStoreWrapper)
    }

    @Singleton
    @Provides
    fun provideKeystoreWrapper(context: Context) : KeyStoreWrapper {
        return KeyStoreWrapper(context)
    }

    @Singleton
    @Provides
    fun provideEncryptionService(context: Context, keyStoreWrapper: KeyStoreWrapper) : CryptoManager{
        return CryptoManager(context,keyStoreWrapper)
    }
}
