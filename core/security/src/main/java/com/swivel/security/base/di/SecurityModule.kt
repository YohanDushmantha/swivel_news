package com.swivel.security.base.di

import android.content.Context
import com.swivel.config.constants.AppStates
import com.swivel.security.root.RootChecker
import com.swivel.security.auth_manager.AuthManger
import com.swivel.shared_pref.SharedPrefManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author Yohan Dushmantha
 * @class SecurityModule
 *
 * Provides security module related dependencies for dagger
 */
@Module
class SecurityModule {

    @Singleton
    @Provides
    fun provideAuthManager(sharedPrefManager: SharedPrefManager,context: Context, appStates: AppStates) : AuthManger{
        return AuthManger(sharedPrefManager,context, appStates)
    }

    @Singleton
    @Provides
    fun provideRootChecker() : RootChecker{
        return RootChecker()
    }

}