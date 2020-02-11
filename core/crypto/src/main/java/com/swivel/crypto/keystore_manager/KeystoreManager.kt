package com.swivel.crypto.keystore_manager

import android.content.Context
import android.os.Build
import androidx.core.content.edit
import com.swivel.crypto.CryptoConstants
import com.swivel.crypto.crypto_manager.CipherWrapper
import com.swivel.crypto.crypto_manager.KeyStoreWrapper
import com.swivel.crypto.crypto_manager.enums.CipherTransformationType
import timber.log.Timber
import java.security.KeyStore
import javax.inject.Inject

/**
 * @author Yohan Dushmantha
 * @class KeyStoreManager
 *
 * manage keystore creation and deletion
 */
class KeystoreManager @Inject constructor(
    private val context: Context,
    private val keyStoreWrapper: KeyStoreWrapper
) {

    fun createMasterKey(keyPassword: String? = null) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            createAndroidSymmetricKey()
        } else {
            createDefaultSymmetricKey()
        }
    }

    fun removeMasterKey() {
        keyStoreWrapper.removeAndroidKeyStoreKey(CryptoConstants.ALIAS)
    }

    private fun createAndroidSymmetricKey() {
        if(!isSigningKey(CryptoConstants.ALIAS)){
            keyStoreWrapper.createAndroidKeyStoreSymmetricKey(CryptoConstants.ALIAS)
        }
    }

    private fun createDefaultSymmetricKey() {
        val symmetricKey = keyStoreWrapper.generateDefaultSymmetricKey()
        val masterKey = keyStoreWrapper.createAndroidKeyStoreAsymmetricKey(CryptoConstants.ALIAS)
        val encryptedSymmetricKey = CipherWrapper(CipherTransformationType.TRANSFORMATION_ASYMMETRIC.type).wrapKey(symmetricKey, masterKey.public)
        keyStoreWrapper.getSessionSharedPreferences().edit {
            //this.putString(dataKey, cryptoManger.encrypt(keystoreAlias.value, dataValue ?: null))
            this.putString(CryptoConstants.SHARED_PREF_STORAGE_KEY_WRAPPER_KEY,encryptedSymmetricKey)
            this.apply()
            this.commit()
        }
    }

    /**
     * check whether singing keys are elready exists
     * @param alias : String? keyStore alias
     * @return Boolean JBMR2+ If Key with the default alias exists, returns true, else false.
     * on pre-JBMR2 returns true always.
     */

    fun isSigningKey(alias: String?): Boolean {
        return try {
            val keyStore = KeyStore.getInstance("AndroidKeyStore")
            keyStore.load(null)
            keyStore.containsAlias(alias)
        } catch (e: Exception) {
            Timber.e(e)
            false
        }
    }
}