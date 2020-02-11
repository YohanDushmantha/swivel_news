package com.swivel.validator.validation_config

import com.wajahatkarim3.easyvalidation.core.view_ktx.*
import com.swivel.validator.R
import com.swivel.validator.ValidationConfigResult

/**
 * @author Yohan Dushmantha
 * @class ValidNameConfig
 *
 * validation config object for validate name
 */
data class ValidNameConfig constructor(
    var minLength : Int = 2,
    var maxLength : Int = 100
) : BaseValidationConfig(){

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
                fieldName,minLength
            )
        }

        //check max length of text
        if(!text?.maxLength(maxLength)!!){
            return createValidationConfigResult(
                 false,
                R.string.validation_max_length_msg,
                fieldName,maxLength
            )
        }

        // check whether there is no numbers
        if(!text?.noNumbers()!!){
            return createValidationConfigResult(
                 false,
                R.string.validation_no_numbers_msg,
                fieldName
            )
        }

        // check whether there is no special characters or not
        if(!text?.noSpecialCharacters()!!){
            return createValidationConfigResult(
                 false,
                R.string.validation_can_not_special_characters_msg,
                fieldName
            )
        }

        //return success response
        return createValidationConfigResult(true)
    }

}