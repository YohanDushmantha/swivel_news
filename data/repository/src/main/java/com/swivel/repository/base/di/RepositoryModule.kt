package com.swivel.repository.base.di

import android.content.Context
import com.swivel.local.dao.PostDao
import com.swivel.remote.news_service.api.news_api.NewsRemoteApi
import com.swivel.remote.post_service.api.post_api.PostRemoteApi
import com.swivel.repository.driver_service_repositories.DriverAuthenticationRepository
import com.swivel.repository.post_service_repositories.PostRepository
import com.swivel.shared_pref.SharedPrefManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author Yohan Dushmantha
 * @class RepositoryModule
 *
 * Provides repository module related dependencies for dagger
 */
@Module
class RepositoryModule {

    /**
     * provide post repository api
     * @param postRemoteApi remote api for manage data of post api
     * @param postDao local db api for mange data of post data
     * @return PostRepository post repository api
     */
    @Provides
    @Singleton
    internal fun providePostRepository(postRemoteApi: PostRemoteApi,postDao: PostDao) : PostRepository {
        return PostRepository(
            postRemoteApi,
            postDao
        )
    }

    /**
     * provide driver authentication repository api
     * @param driverAuthenticationRemoteApi remote api for manage data of driver authentication api
     * @return DriverAuthenticationRemoteApi driver authentication repository api
     */
    @Provides
    @Singleton
    internal fun provideDriverAuthenticationRepository(
        driverAuthenticationRemoteApi: NewsRemoteApi,
        sharedPrefManager: SharedPrefManager,
        context: Context
    ) : DriverAuthenticationRepository{
        return DriverAuthenticationRepository(
            driverAuthenticationRemoteApi,
            sharedPrefManager,
            context
        )
    }

}