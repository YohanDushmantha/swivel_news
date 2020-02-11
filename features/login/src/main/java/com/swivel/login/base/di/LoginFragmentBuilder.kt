package com.swivel.login.base.di

import com.swivel.login.ui.forgot_password_option.ForgotPasswordOptionFragment
import com.swivel.login.ui.verify_password.VerifyPasswordFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @author Yohan Dushmantha
 * @class LoginFragmentBuilder
 *
 * Provide Login Module related fragments and inject dependencies
 */

@Module
abstract class LoginFragmentBuilder {

    @ContributesAndroidInjector
    internal abstract fun bindVerifyPasswordFragment() : VerifyPasswordFragment

    @ContributesAndroidInjector
    internal abstract fun bindForgotPasswordOptionFragment() : ForgotPasswordOptionFragment
}