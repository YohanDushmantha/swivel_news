package com.swivel.repository.swivel_news_service_repositories

import android.content.Context
import com.swivel.crypto.keystore_manager.KEYSTORE_ALIAS
import com.swivel.models.base.DataSource
import com.swivel.models.entities.UserAuthentication
import com.swivel.models.libs.shared_pref.enums.SHARED_PREF_STORAGE_KEY
import com.swivel.repository.core.BaseRepository
import com.swivel.repository.exception.DataSourceNotHandledException
import com.swivel.repository.swivel_news_service_repositories.exceptions.UserAuthenticationException
import com.swivel.shared_pref.SharedPrefManager
import javax.inject.Inject

/**
 * @author Yohan Dushmantha
 * class UserAuthenticationRepository
 */
class UserAuthenticationRepository @Inject constructor(
    private val sharedPrefManager: SharedPrefManager,
    private val context : Context
) : BaseRepository(){

    suspend fun registerUser(
        userAuthentication: UserAuthentication,
        userAuthenticationKey : String,
        dataSource: DataSource? = DataSource.LOCAL,
        customErrorMessage: String? = null
    ) : UserAuthentication? {
        when(dataSource){
            DataSource.LOCAL -> {
                //insert data into shared preferences
                return sharedPrefManager.pushData(
                    context,
                    SHARED_PREF_STORAGE_KEY.SESSION_DATA_KEY,
                    KEYSTORE_ALIAS.DEFAULT,
                    userAuthenticationKey,
                    userAuthentication
                )?.let {
                    if(it){
                        return userAuthentication
                    }
                    return null
                } ?: throw UserAuthenticationException("User authentication object storing process is failed.")
            }

            else -> throw  DataSourceNotHandledException()
        }
    }

}