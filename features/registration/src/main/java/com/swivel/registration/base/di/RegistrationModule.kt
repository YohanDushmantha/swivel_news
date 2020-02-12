package com.swivel.registration.base.di

import android.content.Context
import com.swivel.registration.ui.user_registration.UserRegistrationFormData
import com.swivel.registration.ui.user_registration.UserRegistrationFormValidator
import com.swivel.validator.BaseValidator
import dagger.Module
import dagger.Provides

/**
 * @author Yohan Dushmantha
 * @class RegistrationModule
 *
 * Provides Registration module related dependencies for dagger
 */
@Module
class RegistrationModule {

    @Provides
    fun provideUserRegistrationFormData() : UserRegistrationFormData{
        return UserRegistrationFormData()
    }

    @Provides
    fun provideUserRegistrationFormValidator(context: Context, baseValidator: BaseValidator) : UserRegistrationFormValidator{
        return UserRegistrationFormValidator(context,baseValidator)
    }
}