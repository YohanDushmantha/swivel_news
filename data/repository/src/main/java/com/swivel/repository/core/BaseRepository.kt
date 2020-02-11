package com.swivel.repository.core

import com.swivel.remote.core.Output
import com.swivel.repository.exception.NetworkRequestFailedException
import com.swivel.utility.release_manager.ReleaseManager
import retrofit2.Response
import javax.inject.Inject

/**
 * @author Yohan Dushmantha
 * @class BaseRepository
 *
 * base repository to handle network calls
 */
open class BaseRepository {

    @Inject lateinit var releaseManager: ReleaseManager

    suspend fun <T : Any> safeApiCall(
        call : suspend()-> Response<T>,
        errorMessage : String
    ) :  T?{
        var output : T? = null
        try {
            val result = apiOutput(call, errorMessage)

            when(result){
                is Output.Success ->
                    output = result.output
                is Output.Error ->
                    throw NetworkRequestFailedException(result.exception.message)
            }
            return output
        }catch (ex : Exception){
            throw NetworkRequestFailedException(ex.message)
        }
    }

    private suspend fun<T : Any> apiOutput(call: suspend()-> Response<T>, error: String) : Output<T>{
        try {
            val response = call.invoke()
            if (response.isSuccessful)
                return Output.Success(response.body()!!)
            else
                if(releaseManager.isProductionRelease()){
                    return Output.Error(NetworkRequestFailedException(error))
                }
                return Output.Error(NetworkRequestFailedException(response.message()))
        }catch (ex : Exception){
            if(releaseManager.isProductionRelease()){
                return Output.Error(NetworkRequestFailedException(error))
            }
            return Output.Error(NetworkRequestFailedException(ex.message))

        }
    }


}