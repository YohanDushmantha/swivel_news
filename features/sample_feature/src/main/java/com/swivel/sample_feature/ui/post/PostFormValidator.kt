package com.swivel.sample_feature.ui.post

import android.content.Context
import com.swivel.sample_feature.R
import com.swivel.validator.BaseValidator
import com.swivel.models.libs.navigation.ValidationResult
import com.swivel.validator.exceptions.FormDataNotFoundException
import com.swivel.validator.validation_config.BaseValidationConfig
import com.swivel.validator.validation_config.ValidNameConfig
import com.swivel.validator.validation_config.ValidPhoneNumberConfig
import javax.inject.Inject

/**
 * @author Yohan Dushmantha
 * @class PostFormValidator
 *
 * handle all kind of validations of PostViewModel
 */
class PostFormValidator @Inject constructor(val context: Context?, val baseValidator: BaseValidator){

    /**
     * validate form data of post view model
     * @param postFormData form data object of post
     * @return ValidationResult result of validation
     */

    fun validateFormData(postFormData: PostFormData?) : ValidationResult? {
        postFormData?.let {
            val configList : ArrayList<BaseValidationConfig> = ArrayList()

            configList.add(ValidNameConfig(
            ).apply {
                text = it.firstName
                fieldName = context?.getString(R.string.validation_field_first_name)
            })

            /*configList.add(ValidNameConfig().apply {
                text = it.lastName
                fieldName = context?.getString(R.string.validation_field_last_name)
                isRequired = true
            })*/

            configList.add(ValidPhoneNumberConfig().apply {
                text = it.lastName
                fieldName = "Mobile Number"
                isRequired = true
            })

            return baseValidator.validate(configList,context)
        }
        throw FormDataNotFoundException()
    }
}