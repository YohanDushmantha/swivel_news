package com.swivel.crypto

/**
 * @author Yohan Dushmantha
 * @class CryptoConstants
 *
 * constant values which is need for crypto modules
 */
interface CryptoConstants {
    companion object {
        const val ALIAS = "MASTER_KEY"
        const val IV_SEPARATOR = "]"
        const val ALGORITHM_AES = "AES"
        const val ALGORITHM_RSA = "RSA"
        const val SHARED_PREF_STORAGE_KEY_WRAPPER_KEY = "SHARED_PREF_STORAGE_ENCRYPTION_KEY"
        const val KEYSTORE_PROVIDER_ANDROID_KEYSTORE = "AndroidKeyStore"

        const val PADDING_TYPE = "PKCS1Padding"
        const val BLOCKING_MODE = "NONE"
        const val SIGNATURE_SHA256withRSA = "SHA256withRSA"
        const val SIGNATURE_SHA512withRSA = "SHA512withRSA"
    }
}