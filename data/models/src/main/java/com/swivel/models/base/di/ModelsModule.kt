package com.swivel.models.base.di

import com.swivel.models.base.AppConfig
import com.swivel.models.base.AppConstants
import com.swivel.models.base.RequestHeaders
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author Yohan Dushmantha
 * @class ModelsModule
 *
 * Provides models module related dependencies for dagger
 */
@Module
class ModelsModule {

    @Singleton
    @Provides
    fun providesAppConfig() : AppConfig{
        return AppConfig()
    }

    @Singleton
    @Provides
    fun provideAppConstants() : AppConstants{
        return AppConstants()
    }

    @Provides
    fun provideRequestHeaders() : RequestHeaders{
        return RequestHeaders()
    }
}