package com.swivel.shared.ui.verify_mobile_number

import android.content.Context
import com.swivel.shared.R
import com.swivel.validator.BaseValidator
import com.swivel.models.libs.navigation.ValidationResult
import com.swivel.validator.exceptions.FormDataNotFoundException
import com.swivel.validator.validation_config.BaseValidationConfig
import com.swivel.validator.validation_config.ValidPhoneNumberConfig
import javax.inject.Inject

/**
 * @author Yohan Dushmantha
 * @class VerifyMobileNumberFormValidator
 */
class VerifyMobileNumberFormValidator @Inject constructor(
    private val context: Context?,
    private val baseValidator : BaseValidator
) {

    /**
     * validate form data of verifyMobileNumber view model
     * @param verifyMobileNumberFormData form data object of post
     * @return ValidationResult result of validation
     */

    fun validateFormData(verifyMobileNumberFormData: VerifyMobileNumberFormData?) : ValidationResult? {
        verifyMobileNumberFormData?.let {
            val configResult : ArrayList<BaseValidationConfig> = ArrayList()

            configResult.add(ValidPhoneNumberConfig().apply {
                text = it.mobileNumber
                isRequired = true
                fieldName = context?.getString(R.string.verify_mobile_number_field_name)
                shortFormatLength = 9
                checkWithoutBaseCurrencyOrZero = true
            })

            return baseValidator.validate(configResult,context)
        }

        throw FormDataNotFoundException()
    }
}