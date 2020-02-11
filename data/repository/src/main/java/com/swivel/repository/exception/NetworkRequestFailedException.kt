package com.swivel.repository.exception

class NetworkRequestFailedException constructor(val errorMessage : String? = null) : Exception() {

    override val message: String?
        get() = errorMessage ?: "Network Request Failed Exception"

}