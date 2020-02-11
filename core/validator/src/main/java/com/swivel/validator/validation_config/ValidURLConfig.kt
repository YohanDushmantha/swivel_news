package com.swivel.validator.validation_config

import com.wajahatkarim3.easyvalidation.core.view_ktx.nonEmpty
import com.wajahatkarim3.easyvalidation.core.view_ktx.validUrl
import com.swivel.validator.R
import com.swivel.validator.ValidationConfigResult

/**
 * @author Yohan Dushmantha
 * @class ValidURLConfig
 */
class ValidURLConfig : BaseValidationConfig() {

    override fun validate(): ValidationConfigResult {

        // check whether it is required or not
        if(isRequired && !text?.nonEmpty()!!){
            return createValidationConfigResult(
                 false,
                R.string.validation_required_msg,
                fieldName
            )
        }

        // check whether text is a valid url or not
        if(!text?.validUrl()!!){
            return createValidationConfigResult(
                 false,
                R.string.validation_invalid_url_msg,
                fieldName
            )
        }

        //return success response
        return createValidationConfigResult(true)
    }
}