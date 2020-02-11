package com.swivel.crypto.crypto_manager

import android.annotation.TargetApi
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.security.KeyPairGeneratorSpec
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import com.swivel.crypto.CryptoConstants
import com.swivel.models.libs.shared_pref.enums.SHARED_PREF_STORAGE_KEY
import java.math.BigInteger
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.KeyStore
import java.security.PrivateKey
import java.util.*
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.security.auth.x500.X500Principal

/**
 * @author Yohan Dushmantha
 * @class KeyStoreWrapper
 * This class wraps [KeyStore] class apis with some additional possibilities.
 */
class KeyStoreWrapper(
    private val context: Context
) {

    private val keyStore: KeyStore = createAndroidKeyStore()

    fun getAndroidKeyStoreSymmetricKey(alias: String): SecretKey? = keyStore.getKey(alias, null) as SecretKey?

    fun getAndroidKeyStoreAsymmetricKeyPair(alias: String): KeyPair? {
        val privateKey = keyStore.getKey(alias, null) as PrivateKey?
        val publicKey = keyStore.getCertificate(alias)?.publicKey

        return if (privateKey != null && publicKey != null) {
            KeyPair(publicKey, privateKey)
        } else {
            null
        }
    }

    fun createAndroidKeyStoreAsymmetricKey(alias: String): KeyPair {
        val generator = KeyPairGenerator.getInstance(CryptoConstants.ALGORITHM_RSA, CryptoConstants.KEYSTORE_PROVIDER_ANDROID_KEYSTORE)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            initGeneratorWithKeyGenParameterSpec(generator, alias)
        }else{
            initGeneratorWithKeyPairGeneratorSpec(generator, alias)
        }

        return generator.generateKeyPair()
    }

    @TargetApi(23)
    fun createAndroidKeyStoreSymmetricKey(alias: String): SecretKey {
        val keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, CryptoConstants.KEYSTORE_PROVIDER_ANDROID_KEYSTORE)
        val builder = KeyGenParameterSpec.Builder(alias, KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT)
            .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
        keyGenerator.init(builder.build())
        return keyGenerator.generateKey()
    }

    fun removeAndroidKeyStoreKey(alias: String) = keyStore.deleteEntry(alias)

    fun generateDefaultSymmetricKey(): SecretKey {
        val keyGenerator = KeyGenerator.getInstance(CryptoConstants.ALGORITHM_AES)
        return keyGenerator.generateKey()
    }

    private fun initGeneratorWithKeyPairGeneratorSpec(generator: KeyPairGenerator, alias: String) {
        val startDate = Calendar.getInstance()
        val endDate = Calendar.getInstance()
        endDate.add(Calendar.YEAR, 20)

        val builder = KeyPairGeneratorSpec.Builder(context)
            .setAlias(alias)
            .setSerialNumber(BigInteger.ONE)
            .setSubject(X500Principal("CN=${alias} CA Certificate"))
            .setStartDate(startDate.time)
            .setEndDate(endDate.time)

        generator.initialize(builder.build())
    }

    @TargetApi(Build.VERSION_CODES.M)
    private fun initGeneratorWithKeyGenParameterSpec(generator: KeyPairGenerator, alias: String) {
        val builder = KeyGenParameterSpec.Builder(alias, KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT)
            .setBlockModes(KeyProperties.BLOCK_MODE_ECB)
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_RSA_PKCS1)
        generator.initialize(builder.build())
    }

    private fun createAndroidKeyStore(): KeyStore {
        val keyStore = KeyStore.getInstance(CryptoConstants.KEYSTORE_PROVIDER_ANDROID_KEYSTORE)
        keyStore.load(null)
        return keyStore
    }

    fun getSessionSharedPreferences() : SharedPreferences {
        return context.getSharedPreferences(
            SHARED_PREF_STORAGE_KEY.SESSION_DATA_KEY.KEY,
            Context.MODE_PRIVATE
        )
    }
}
