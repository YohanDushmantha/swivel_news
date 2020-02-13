package com.swivel.models.entities

/**
 * @author Yohan Dushmantha
 * @class User
 * contains user related informations
 */
data class User constructor(
    val firstName : String? = null,
    val lastName : String? = null,
    val email : String? = null,
    val password : String? = null
)