package com.swivel.validator.base.di

import com.swivel.validator.BaseValidator
import com.swivel.validator.IBaseValidator
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author Yohan Dushmantha
 * @class ValidatorModule
 *
 * Provides Validation module related dependencies for dagger
 */
@Module
class ValidatorModule {

    @Singleton
    @Provides
    fun provideBaseValidator() : IBaseValidator {
        return BaseValidator()
    }
}