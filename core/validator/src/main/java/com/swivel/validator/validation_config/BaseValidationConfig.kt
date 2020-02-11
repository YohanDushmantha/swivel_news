package com.swivel.validator.validation_config

import com.swivel.validator.ValidationConfigResult

/**
 * @author Yohan Dushmantha
 * @class BaseValidationConfig
 *
 * generic type to keep validation config classes for each validation methods
 *
 * this class should be implemented by each and every validation config class
 *
 * Developer can create custom validation config object for validate any data
 */
abstract class BaseValidationConfig {
    // text for validate
    var text : String? = null

    // field name for formatting error or success message
    var fieldName : String? = null

    // required status of the field. if this is true, should be checked whether it is empty or not
    var isRequired : Boolean = false

    // required status of success message, if this attribute is true, should be return success
    // message with validation result
    var isSuccessMessageRequired : Boolean = false

    // override custom error message
    var customErrorMessage : String? = null

    // override custom success message
    var customSuccessMessage : String? = null

    /**
     * this method should be overridden by every validation config class
     * @param context application context class to retrieve and format xml resources
     */
    abstract fun validate() : ValidationConfigResult

    /**
     * format validation messages with format arguments
     * @param context application context
     * @param isValid validation result flag
     * @param resourceID resource id of related string
     * @param argumentList argument list to format resource
     * @return ValidationResult validation result object
     */
    fun createValidationConfigResult(isValid : Boolean, resourceID : Int? = null, vararg argumentList : Any?) : ValidationConfigResult{
        return  ValidationConfigResult(isValid).apply {
            this.resourceId = resourceID
            this.isSuccessMessageRequired = this@BaseValidationConfig.isSuccessMessageRequired
            this.customErrorMessage = this@BaseValidationConfig.customErrorMessage
            this.customSuccessMessage = this@BaseValidationConfig.customSuccessMessage
            this.argumentList = argumentList
        }
    }
}