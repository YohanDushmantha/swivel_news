package com.swivel.repository.base.di

import android.content.Context
import com.swivel.local.dao.PostDao
import com.swivel.remote.news_service.api.news_api.NewsRemoteApi
import com.swivel.remote.post_service.api.post_api.PostRemoteApi
import com.swivel.repository.swivel_news_service_repositories.UserAuthenticationRepository
import com.swivel.repository.post_service_repositories.PostRepository
import com.swivel.repository.swivel_news_service_repositories.NewsRepository
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
     * provide user authentication repository api
     * @param sharedPrefManager datasource class for saving data into local storage
     * @param application context
     */
    @Provides
    @Singleton
    internal fun provideUserAuthenticationRepository(
        sharedPrefManager: SharedPrefManager,
        context : Context
    ) : UserAuthenticationRepository{
        return UserAuthenticationRepository(
            sharedPrefManager,
            context
        )
    }

    /**
     * provide news repository api
     * @param context application context
     * @param newsRemoteApi datasource class for retrieving news from remotely
     */
    @Provides
    @Singleton
    internal fun provideNewsRepository(context: Context,newsRemoteApi: NewsRemoteApi) : NewsRepository{
        return NewsRepository(context,newsRemoteApi)
    }

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

}