package com.swivel.onboarding.base.di

import com.swivel.onboarding.ui.walkthrough.WalkthroughViewPagerAdaptor
import dagger.Module
import dagger.Provides

/**
 * @author Yohan Dushmantha
 * @class OnboardingModule
 *
 * Provides onboarding module related dependencies for dagger
 */
@Module
class OnboardingModule {

    /**---------------------------------------------------------------------------------------------*
     * WALKTHROUGH - START
     *----------------------------------------------------------------------------------------------*/

    @Provides
    fun providesWalkthroughViewPagerAdaptor() : WalkthroughViewPagerAdaptor{
        return WalkthroughViewPagerAdaptor()
    }

    /**---------------------------------------------------------------------------------------------*
     * WALKTHROUGH - END
     *----------------------------------------------------------------------------------------------*/

}