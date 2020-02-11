package com.swivel.remote.core

import java.lang.Exception

/**
 * @author Yohan Dushmantha
 * @class Output
 *
 * Output class is a data wrapper for handle remote request and response
 */
sealed class Output<out T : Any>{
    data class Success<out T : Any> (val output : T) : Output<T>()
    data class Error(val exception: Exception) : Output<Nothing>()
}