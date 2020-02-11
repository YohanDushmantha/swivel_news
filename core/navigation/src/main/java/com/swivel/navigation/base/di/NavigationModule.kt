package com.swivel.navigation.base.di

import com.swivel.navigation.router.Router
import com.swivel.navigation.router.deep_link_router.DeepLinkRouter
import com.swivel.navigation.router.navigation_router.NavigationRouter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author Yohan Dushmantha
 * @class NavigationModule
 *
 * Provides navigation module related dependencies for dagger
 */

@Module
class NavigationModule {

    /**
     * provide Router using argmuments
     * @param deepLinkRouter deepLinkRouter object for url navigation
     * @param navigationRouter instance NavigationRouter
     * @return Router return router object for dagger
     */
    @Provides
    @Singleton
    fun provideRouter(deepLinkRouter: DeepLinkRouter, navigationRouter: NavigationRouter) : Router{
        return Router(deepLinkRouter,navigationRouter)
    }

    /**
     * provide Deep Link Router
     * @return DeepLinkRouter return deepLinkRouter object for dagger
     */
    @Provides
    @Singleton
    fun provideDeepLinkRouter() : DeepLinkRouter{
        return DeepLinkRouter()
    }

    /**
     * provide Navigation Router
     * @return NavigationRouter return NavigationRouter object for dagger
     */
    @Provides
    @Singleton
    fun provideNavigationRouter() : NavigationRouter{
        return NavigationRouter()
    }

}