package com.swivel.shared.base.di

import android.content.Context
import com.swivel.shared.ui.verify_mobile_number.VerifyMobileNumberFormData
import com.swivel.shared.ui.verify_mobile_number.VerifyMobileNumberFormValidator
import com.swivel.validator.BaseValidator
import dagger.Module
import dagger.Provides

/**
 * @author Yohan Dushmantha
 * @class SharedModule
 *
 * Provides shared module related dependencies for dagger
 */

@Module
class SharedModule {

    @Provides
    fun provideVerifyMobileNumberFormData() : VerifyMobileNumberFormData{
        return VerifyMobileNumberFormData()
    }

    @Provides
    fun provideVerifyMobileNumberFormValidator(context: Context?, baseValidator: BaseValidator)
    : VerifyMobileNumberFormValidator{
        return VerifyMobileNumberFormValidator(context,baseValidator)
    }

}