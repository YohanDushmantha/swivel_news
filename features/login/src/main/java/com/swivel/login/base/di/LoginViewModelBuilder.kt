package com.swivel.login.base.di

import androidx.lifecycle.ViewModel
import com.swivel.config.di.annotation.ViewModelKey
import com.swivel.login.ui.forgot_password_option.ForgotPasswordOptionViewModel
import com.swivel.login.ui.verify_password.VerifyPasswordViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * @author Yohan Dushmantha
 * @class LoginViewModelBuilder
 *
 * Provide Login Module related viewModels and inject dependencies
 */

@Module
abstract class LoginViewModelBuilder {

    @Binds
    @IntoMap
    @ViewModelKey(VerifyPasswordViewModel::class)
    internal abstract fun bindVerifyPasswordViewModel(viewModel: VerifyPasswordViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ForgotPasswordOptionViewModel::class)
    internal abstract fun bindForgotPasswordOptionViewModel(viewModel: ForgotPasswordOptionViewModel) : ViewModel

}