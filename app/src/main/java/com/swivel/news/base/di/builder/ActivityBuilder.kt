package com.swivel.news.base.di.builder

import com.swivel.home.base.di.HomeFragmentBuilder
import com.swivel.home.base.di.HomeViewModelBuilder
import com.swivel.login.base.di.LoginFragmentBuilder
import com.swivel.login.base.di.LoginViewModelBuilder
import com.swivel.onboarding.base.di.OnboardingFragmentBuilder
import com.swivel.onboarding.base.di.OnboardingViewModelBuilder
import com.swivel.profile.base.di.ProfileFragmentBuilder
import com.swivel.profile.base.di.ProfileViewModelBuilder
import com.swivel.registration.base.di.RegistrationFragmentBuilder
import com.swivel.registration.base.di.RegistrationViewModelBuilder
import com.swivel.sample_feature.base.di.SampleFeatureFragmentBuilder
import com.swivel.sample_feature.base.di.SampleFeatureViewModelBuilder
import com.swivel.shared.base.di.SharedFragmentBuilder
import com.swivel.shared.base.di.SharedViewModelBuilder
import com.swivel.news.AppViewModelBuilder
import com.swivel.news.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @author Yohan Dushmantha
 * @class ActivityBuilder
 *
 * Activity Builder is the main module that is responsible for injecting fragments and viewModels
 * for all feature modules
 *
 * FragmentBuilder and ViewModelBuilder of every feature module should be included here
 *
 */
@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(
        modules = [

            //App Module
            AppViewModelBuilder::class,

            // Onboarding Module
            OnboardingFragmentBuilder::class,
            OnboardingViewModelBuilder::class,

            // Login Module
            LoginFragmentBuilder::class,
            LoginViewModelBuilder::class,

            // RegistrationModule
            RegistrationFragmentBuilder::class,
            RegistrationViewModelBuilder::class,

            // Home Module
            HomeFragmentBuilder::class,
            HomeViewModelBuilder::class,

            // Profile Module
            ProfileFragmentBuilder::class,
            ProfileViewModelBuilder::class,

            // Shared Module
            SharedFragmentBuilder::class,
            SharedViewModelBuilder::class,

            // Sample Feature Module
            SampleFeatureFragmentBuilder::class,
            SampleFeatureViewModelBuilder::class

        ]
    )
    internal abstract fun bindMainActivity() : MainActivity

}