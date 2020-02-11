package com.swivel.config.di

import com.swivel.config.constants.AppStates
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author Yohan Dushmantha
 * @class ConfigModule
 *
 * Provides config module related dependencies for dagger
 */
@Module
class ConfigModule {

    /**
     * provide app states object for dagger dependency pool
     */
    @Provides
    @Singleton
    fun provideAppStates() : AppStates{
        return AppStates()
    }
}