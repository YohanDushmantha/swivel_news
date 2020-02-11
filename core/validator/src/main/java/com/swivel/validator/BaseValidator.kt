package com.swivel.validator

import android.content.Context
import com.swivel.models.libs.navigation.ValidationResult
import com.swivel.validator.exceptions.ValidationConfigListNotFound
import com.swivel.validator.exceptions.ValidationConfigNotFound
import com.swivel.validator.exceptions.ValidationConfigResultNotFound
import com.swivel.validator.validation_config.BaseValidationConfig
import javax.inject.Inject

/**
 * @author Yohan Dushmantha
 * @class BaseValidator
 *
 * handle validation and return validation result according to received validation config objects
 */
class BaseValidator @Inject constructor() : IBaseValidator {

    /**
     * validate received data according to validationConfig list and return validation result
     * @param validationConfigList list of validation config
     * @param context application context for formatting resource files
     * @return ValidationResult result of validation proccess
     * @throws if validaitonConfigList is equal to null or empty returns ValidationConfigListNotFound
     * Exception
     */
    override fun validate(validationConfigList: ArrayList<BaseValidationConfig>?, context: Context?): ValidationResult {
        if(validationConfigList != null && validationConfigList.size > 0){
            validationConfigList.forEach {
                val result = validateField(it,context)
                if(!result.isValid){
                    return result
                }
            }
            return ValidationResult(true)
        }else{
            throw ValidationConfigListNotFound()
        }
    }

    /**
     * validate received data according to validationConfig and return validation result
     * @param validationConfig validation config object
     * @param context application context for formatting resource files
     * @return ValidationResult result of validation proccess
     * @throws ValidationConfigNotFound if validationConfig not found return ValidationConfigNotFound
     * Exception
     */
    override fun validate(validationConfig: BaseValidationConfig?, context: Context?) : ValidationResult {
        validationConfig?.let {
            val result = validateField(it,context)
            when (!result.isValid){
                true -> {result}
                false -> {
                    ValidationResult(true)
                }
            }
        }
        throw ValidationConfigNotFound()
    }

    /**
     * invoke validate method inside validation config object
     * @param validationConfig validationConfig validation config object
     * @param context context application context for formatting resource files
     * @return ValidationResult ValidationResult result of validation proccess
     */
    private fun validateField(validationConfig: BaseValidationConfig, context: Context?) : ValidationResult {
        return formatValidationMessage(context,validationConfig.validate())
    }

    /**
     * format validation messages with format arguments
     * @param context application context
     * @param validationConfigResult result of validation
     * @return ValidationResult validation result object
     * @throws ValidationConfigResultNotFound the exception throws when not receiving validation
     * config result from validation config object
     */
    fun formatValidationMessage(context: Context? , validationConfigResult : ValidationConfigResult?) : ValidationResult {
        validationConfigResult?.let {
            if(!it.isValid){
                if(it.customErrorMessage == null && it.resourceId != null){
                    return getValidationResult(it.isValid,context?.getString(it.resourceId!!, *it.argumentList))
                }
                return getValidationResult(it.isValid,it.customErrorMessage)
            }else{
                if(it.customSuccessMessage == null && it.isSuccessMessageRequired && it.resourceId != null){
                    return getValidationResult(it.isValid,context?.getString(it.resourceId!!, *it.argumentList))
                }
                return getValidationResult(it.isValid,it.customSuccessMessage)
            }
        }
        throw ValidationConfigResultNotFound()
    }

    /**
     * return validation result object
     * @param isValid validation result flag
     * @param message formatted message
     * @return ValidationResult validation result object
     */
    fun getValidationResult(isValid: Boolean, message : String?): ValidationResult {
        return ValidationResult(
            isValid,
            message
        )
    }

    /**---------------------------------------------------------------------------------------------*
     * VALIDATE - END
     *----------------------------------------------------------------------------------------------*/
}