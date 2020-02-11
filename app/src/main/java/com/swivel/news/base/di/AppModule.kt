package com.swivel.news.base.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author Yohan Dushmantha
 * @class AppModule
 *
 * Provides app module related dependencies for dagger
 */
@Module(subcomponents = [MainActivityComponent::class])
class AppModule {

    @Provides
    @Singleton
    internal fun provideContext(application : Application) : Context{
        return application
    }

}