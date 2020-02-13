package com.swivel.registration.base.di

import androidx.lifecycle.ViewModel
import com.swivel.config.di.annotation.ViewModelKey
import com.swivel.registration.ui.user_registration.UserRegistrationViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * @author Yohan Dushmantha
 * @class RegistrationViewModelBuilder
 *
 * Provide Registration Module related viewModels and inject dependencies
 */

@Module
abstract class RegistrationViewModelBuilder {

    @Binds
    @IntoMap
    @ViewModelKey(UserRegistrationViewModel::class)
    internal abstract fun bindUserRegistrationViewModel(viewModel: UserRegistrationViewModel) : ViewModel
}