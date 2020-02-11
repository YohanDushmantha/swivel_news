package com.swivel.onboarding.base.di

import com.swivel.onboarding.ui.splash.SplashFragment
import com.swivel.onboarding.ui.walkthrough.WalkthroughFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @author Yohan Dushmantha
 * @class OnboardingFragmentBuilder
 *
 * Provide Onboarding Module related fragments and inject dependencies
 */

@Module
abstract class OnboardingFragmentBuilder {

    @ContributesAndroidInjector
    internal abstract fun bindSplashFragment() : SplashFragment

    @ContributesAndroidInjector
    internal abstract fun bindWalkthroughFragment() : WalkthroughFragment
}