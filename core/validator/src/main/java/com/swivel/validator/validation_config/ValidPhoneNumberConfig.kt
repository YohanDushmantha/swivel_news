package com.swivel.validator.validation_config

import com.wajahatkarim3.easyvalidation.core.view_ktx.noSpecialCharacters
import com.wajahatkarim3.easyvalidation.core.view_ktx.onlyNumbers
import com.swivel.validator.R
import com.swivel.validator.ValidationConfigResult

/**
 * @author Yohan Dushmantha
 * @class ValidPhoneNumberConfig
 *
 * validation config object for validate phone number
 */
class ValidPhoneNumberConfig constructor(
    var shortFormatLength : Int? = 10,
    var longFormatLength : Int? = 12,
    var baseCountryCode : String? = "94",
    var checkWithoutBaseCurrencyOrZero : Boolean = false
) : BaseValidationConfig() {

    override fun validate(): ValidationConfigResult {
        // check whether it is required or not
        if(isRequired && text?.isEmpty()!!){
            return createValidationConfigResult(
                 false,
                R.string.validation_required_msg,
                fieldName
            )
        }


        if(text?.length!! == shortFormatLength){
            // check whether contains only numbers
            if(!text?.onlyNumbers()!!){
                return createValidationConfigResult(
                     false,
                    R.string.validation_contain_characters_msg,
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

            // when checkWithoutBaseCurrencyOrZero == true && first character of text equels to 0, should
            // return error
            if(checkWithoutBaseCurrencyOrZero && text?.get(0)?.equals("0")!!){
                return createValidationConfigResult(
                    false,
                    R.string.validation_invalid_phone_msg,
                    fieldName
                )
            }

            // return success result
            return createValidationConfigResult(true)
        }else if(text?.length!! == longFormatLength){
            // check whether text is starts with + sign or not
            if(!text?.get(0)?.equals('+')!!){
                return createValidationConfigResult(
                    false,
                    R.string.validation_invalid_phone_msg,
                    fieldName
                )
            }

            // check country code
            if(!(text?.substring(1,baseCountryCode?.length!!+1)?.equals(baseCountryCode?.trim())!!)){
                return createValidationConfigResult(
                     false,
                    R.string.validation_invalid_phone_msg,
                    fieldName
                )
            }

            // check whether it's a valid phone number
            if(!text?.substring(1)?.onlyNumbers()!!){
                return createValidationConfigResult(
                     false,
                    R.string.validation_contain_characters_msg,
                    fieldName
                )
            }

            // return success result
            return createValidationConfigResult(true)
        }else{
            return createValidationConfigResult(
                false,
                R.string.validation_invalid_phone_number_length_msg,
                fieldName
            )
        }


    }
}