package com.swivel.validator.validation_config

import com.wajahatkarim3.easyvalidation.core.view_ktx.nonEmpty
import com.wajahatkarim3.easyvalidation.core.view_ktx.validNumber
import com.swivel.validator.R
import com.swivel.validator.ValidationConfigResult

/**
 * @author Yohan Dushmantha
 * @class ValidNumberConfig
 *
 * validation config object for validate number
 */
class ValidNumberConfig : BaseValidationConfig() {
    override fun validate(): ValidationConfigResult {

        // check whether it is required or not
        if(isRequired && !text?.nonEmpty()!!){
            return createValidationConfigResult(
                 false,
                R.string.validation_required_msg,
                fieldName
            )
        }

        // check whether it is valid number or not
        if(!text?.validNumber()!!){
            return createValidationConfigResult(
                false,
                R.string.validation_valid_number_msg,
                fieldName
            )
        }

        //return success response
        return createValidationConfigResult(true)
    }
}