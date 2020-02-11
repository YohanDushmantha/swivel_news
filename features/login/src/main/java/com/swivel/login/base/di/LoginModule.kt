package com.swivel.login.base.di

import android.content.Context
import com.swivel.config.constants.AppStates
import com.swivel.login.ui.verify_password.VerifyPasswordFormData
import com.swivel.login.ui.verify_password.VerifyPasswordFormValidator
import com.swivel.validator.BaseValidator
import dagger.Module
import dagger.Provides

/**
 * @author Yohan Dushmantha
 * @class LoginModule
 *
 * Provides login module related dependencies for dagger
 */
@Module
class LoginModule {

    @Provides
    fun provideVerifyPasswordFormData() : VerifyPasswordFormData{
        return VerifyPasswordFormData()
    }

    @Provides
    fun provideVerifyPasswordFormValidator(context: Context, baseValidator: BaseValidator,appStates: AppStates): VerifyPasswordFormValidator{
        return VerifyPasswordFormValidator(context,baseValidator,appStates)
    }
}