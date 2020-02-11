package com.swivel.validator

import android.content.Context
import com.swivel.models.libs.navigation.ValidationResult
import com.swivel.validator.validation_config.BaseValidationConfig

/**
 * @author Yohan Dushmantha
 * @class IBaseValidator
 *
 * handle validation and return validation result according to received validation config objects
 */
interface IBaseValidator {

    /**
     * validate received data according to validationConfig list and return validation result
     * @param validationConfigList list of valildation config
     * @param context application context for formatting resource files
     * @return ValidationResult result of validation proccess
     */
    fun validate(validationConfigList : ArrayList<BaseValidationConfig>?, context: Context?) : ValidationResult

    /**
     * validate received data according to validationConfig and return validation result
     * @param validationConfig validation config object
     * @param context application context for formatting resource files
     * @return ValidationResult result of validation proccess
     */
    fun validate(validationConfig: BaseValidationConfig?, context: Context?) : ValidationResult
}