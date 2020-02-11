package com.swivel.crypto.crypto_manager
import android.content.Context
import android.os.Build
import com.swivel.crypto.CryptoConstants
import com.swivel.crypto.crypto_manager.enums.CipherTransformationType
import javax.crypto.Cipher
import javax.crypto.SecretKey

/**
 * @author Yohan Dushmantha
 * @class CryptoManager
 * provide capability to encrypt and decrypt data
 */
class CryptoManager(
    private val context: Context,
    private val keyStoreWrapper: KeyStoreWrapper
) {



    /*
     * Encryption Stage
     */



    fun encrypt(data: String, keyPassword: String? = null): String {
        return  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            encryptWithAndroidSymmetricKey(data)
        } else {
            encryptWithDefaultSymmetricKey(data)
        }
    }

    fun decrypt(data: String, keyPassword: String? = null): String {
        return  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            decryptWithAndroidSymmetricKey(data)
        } else {
            decryptWithDefaultSymmetricKey(data)
        }
    }




    private fun encryptWithAndroidSymmetricKey(data: String): String {
        val masterKey = keyStoreWrapper.getAndroidKeyStoreSymmetricKey(CryptoConstants.ALIAS)
        return CipherWrapper(CipherTransformationType.TRANSFORMATION_SYMMETRIC.type).encrypt(data, masterKey, true)
    }

    private fun decryptWithAndroidSymmetricKey(data: String): String {
        val masterKey = keyStoreWrapper.getAndroidKeyStoreSymmetricKey(CryptoConstants.ALIAS)
        return CipherWrapper(CipherTransformationType.TRANSFORMATION_SYMMETRIC.type).decrypt(data, masterKey, true)
    }

    private fun encryptWithDefaultSymmetricKey(data: String): String {
        val masterKey = keyStoreWrapper.getAndroidKeyStoreAsymmetricKeyPair(CryptoConstants.ALIAS)
        val encryptionKey = keyStoreWrapper.getSessionSharedPreferences().getString(CryptoConstants.SHARED_PREF_STORAGE_KEY_WRAPPER_KEY,null)
        val symmetricKey = CipherWrapper(CipherTransformationType.TRANSFORMATION_ASYMMETRIC.type).unWrapKey(encryptionKey!!, CryptoConstants.ALGORITHM_AES, Cipher.SECRET_KEY, masterKey?.private) as SecretKey
        return CipherWrapper(CipherTransformationType.TRANSFORMATION_SYMMETRIC.type).encrypt(data, symmetricKey, true)
    }

    private fun decryptWithDefaultSymmetricKey(data: String): String {
        val masterKey = keyStoreWrapper.getAndroidKeyStoreAsymmetricKeyPair(CryptoConstants.ALIAS)
        val encryptionKey = keyStoreWrapper.getSessionSharedPreferences().getString(CryptoConstants.SHARED_PREF_STORAGE_KEY_WRAPPER_KEY,null)
        val symmetricKey = CipherWrapper(CipherTransformationType.TRANSFORMATION_ASYMMETRIC.type).unWrapKey(encryptionKey!!, CryptoConstants.ALGORITHM_AES, Cipher.SECRET_KEY, masterKey?.private) as SecretKey
        return CipherWrapper(CipherTransformationType.TRANSFORMATION_SYMMETRIC.type).decrypt(data, symmetricKey, true)
    }

}