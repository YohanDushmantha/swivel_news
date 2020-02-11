package com.swivel.onboarding.base.di

import androidx.lifecycle.ViewModel
import com.swivel.config.di.annotation.ViewModelKey
import com.swivel.onboarding.ui.splash.SplashViewModel
import com.swivel.onboarding.ui.walkthrough.WalkthroughViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * @author Yohan Dushmantha
 * @class OnboardingViewModelBuilder
 *
 * Provide Onboarding Module related viewModels and inject dependencies
 */

@Module
abstract class OnboardingViewModelBuilder {

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    internal abstract fun bindSplashViewModel(viewModel: SplashViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(WalkthroughViewModel::class)
    internal abstract fun bindWalkthroughViewModel(viewModel: WalkthroughViewModel) : ViewModel

}