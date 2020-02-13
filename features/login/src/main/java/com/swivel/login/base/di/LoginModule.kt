package com.swivel.login.base.di

import android.content.Context
import com.swivel.config.constants.AppStates
import com.swivel.login.ui.login.LoginFormData
import com.swivel.login.ui.login.LoginFormValidator
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
    /** --------------------------------------------------------------------------------------------
     * LOGIN - START
     * ------------------------------------------------------------------------------------------ */
    @Provides
    fun provideLoginFormData() : LoginFormData{
        return LoginFormData()
    }

    @Provides
    fun provideLoginFormValidator(context: Context, baseValidator: BaseValidator) : LoginFormValidator{
        return LoginFormValidator(context,baseValidator)
    }

    /** --------------------------------------------------------------------------------------------
     * LOGIN - END
     * ------------------------------------------------------------------------------------------ */

    /** --------------------------------------------------------------------------------------------
     * VERIFY PASSWORD - START
     * ------------------------------------------------------------------------------------------ */

    @Provides
    fun provideVerifyPasswordFormData() : VerifyPasswordFormData{
        return VerifyPasswordFormData()
    }

    @Provides
    fun provideVerifyPasswordFormValidator(context: Context, baseValidator: BaseValidator,appStates: AppStates): VerifyPasswordFormValidator{
        return VerifyPasswordFormValidator(context,baseValidator,appStates)
    }

    /** --------------------------------------------------------------------------------------------
     * VERIFY PASSWORD - END
     * ------------------------------------------------------------------------------------------ */
}