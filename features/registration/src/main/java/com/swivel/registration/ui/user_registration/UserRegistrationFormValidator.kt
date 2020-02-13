package com.swivel.registration.ui.user_registration

import android.content.Context
import com.swivel.models.libs.navigation.ValidationResult
import com.swivel.registration.R
import com.swivel.validator.BaseValidator
import com.swivel.validator.exceptions.FormDataNotFoundException
import com.swivel.validator.validation_config.BaseValidationConfig
import com.swivel.validator.validation_config.ValidEmailConfig
import com.swivel.validator.validation_config.ValidNameConfig
import com.swivel.validator.validation_config.ValidPasswordConfig
import javax.inject.Inject

/**
 * @author Yohan Dushmantha
 * @class UserRegistrationFormValidator
 */
class UserRegistrationFormValidator @Inject constructor(
    private val context : Context,
    private val baseValidator : BaseValidator
) {

    /**
     * validate form data of user registratoin view model
     * @param  form data object of user registration
     * @return ValidationResult result of validation
     */
    fun validateFormData(
        userRegistrationFormData: UserRegistrationFormData?
    ) : ValidationResult {
        userRegistrationFormData?.let {
            val configResult : ArrayList<BaseValidationConfig> = ArrayList()

            configResult.add(ValidNameConfig().apply {
                fieldName = context.getString(R.string.user_registration_form_label_firstName).capitalize()
                text = it.firstName
                isRequired = true
            })

            configResult.add(ValidNameConfig().apply {
                fieldName = context.getString(R.string.user_registration_form_label_lastName).capitalize()
                text = it.lastName
                isRequired = true
            })

            configResult.add(ValidEmailConfig().apply {
                fieldName = context.getString(R.string.user_registration_form_label_email).capitalize()
                text = it.email
                isRequired = true

            })

            configResult.add(ValidPasswordConfig().apply {
                text = it.password
                isRequired = true
                retypePassword = it.retypePassword
            })

            return baseValidator.validate(configResult,context)
        }

        throw FormDataNotFoundException()
    }

}