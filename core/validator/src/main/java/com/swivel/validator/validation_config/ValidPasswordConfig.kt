package com.swivel.validator.validation_config

import com.wajahatkarim3.easyvalidation.core.view_ktx.nonEmpty
import com.swivel.validator.R
import com.swivel.validator.ValidationConfigResult

/**
 * @author Yohan Dushmantha
 * @class ValidPasswordConfig
 *
 * validation config object for validate password
 */
class ValidPasswordConfig constructor(
    var passwordPolicy : String? = null,
    var passwordPolicyHint : String? = null
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

        return passwordPolicy?.let {
            if(text?.matches(it.toRegex())!!){
                createValidationConfigResult(true)
            }else{
                customErrorMessage = passwordPolicyHint
                createValidationConfigResult(
                    false,null
                )
            }
        } ?: createValidationConfigResult(true)
    }

}