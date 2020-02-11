package com.swivel.crypto.crypto_manager.enums

/**
 * @author Yohan Dushmantha
 * @class CipherTransformationType
 * Cipher transformation types
 */
enum class CipherTransformationType (val type : String) {
    TRANSFORMATION_ASYMMETRIC("RSA/ECB/PKCS1Padding"),
    TRANSFORMATION_SYMMETRIC("AES/CBC/PKCS7Padding")
}