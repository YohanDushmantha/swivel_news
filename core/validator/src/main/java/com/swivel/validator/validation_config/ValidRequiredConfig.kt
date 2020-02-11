package com.swivel.validator.validation_config

import com.wajahatkarim3.easyvalidation.core.view_ktx.nonEmpty
import com.swivel.validator.R
import com.swivel.validator.ValidationConfigResult

/**
 * @author Yohan Dushmantha
 * @class ValidRequiredConfig
 *
 * validation config object for validate empty texts
 */
class ValidRequiredConfig : BaseValidationConfig(){

    override fun validate(): ValidationConfigResult {
        // check whether it is required or not
        if(isRequired && !text?.nonEmpty()!!){
            return createValidationConfigResult(
                 false,
                R.string.validation_required_msg,
                fieldName
            )
        }

        //return success response
        return createValidationConfigResult(true)
    }
}