package com.swivel.registration.base.di

import com.swivel.registration.ui.user_registration.UserRegistrationFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @author Yohan Dushmantha
 * @class RegistrationFragmentBuilder
 *
 * Provide Registration Module related fragments and inject dependencies
 */

@Module
abstract class RegistrationFragmentBuilder {

    @ContributesAndroidInjector
    internal abstract fun bindUserRegistrationFragment() : UserRegistrationFragment
}