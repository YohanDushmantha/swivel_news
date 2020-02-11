package com.swivel.validator.validation_config

import com.wajahatkarim3.easyvalidation.core.view_ktx.*
import com.swivel.validator.R
import com.swivel.validator.ValidationConfigResult

/**
 * @author Yohan Dushmantha
 * @class ValidEmailConfig
 *
 * validation config object for validate email
 */
data class ValidEmailConfig constructor(
    var minLength : Int = 2,
    var maxLength : Int = 150
) : BaseValidationConfig() {

    override fun validate(): ValidationConfigResult {
        // check whether it is required or not
        if(isRequired && !text?.nonEmpty()!!){
            return createValidationConfigResult(
                 false,
                R.string.validation_required_msg,
                fieldName
            )
        }

        // check min length of text
        if(!text?.minLength(minLength)!!){
            return createValidationConfigResult(
                 false,
                R.string.validation_min_length_msg,
                fieldName, minLength
            )
        }

        //check max length of text
        if(!text?.maxLength(maxLength)!!){
            return createValidationConfigResult(
                 false,
                R.string.validation_max_length_msg,
                fieldName, maxLength
            )
        }

        // check whether it is valid email or not
        if(!text?.validEmail()!!){
            return createValidationConfigResult(
                false,
                R.string.validation_invalid_email_msg,
                fieldName
            )
        }

        // return success response
        return createValidationConfigResult(
            true
        )
    }
}