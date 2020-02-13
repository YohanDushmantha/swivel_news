package com.swivel.login.ui.login

import android.content.Context
import com.swivel.login.R
import com.swivel.models.libs.navigation.ValidationResult
import com.swivel.validator.BaseValidator
import com.swivel.validator.exceptions.FormDataNotFoundException
import com.swivel.validator.validation_config.BaseValidationConfig
import com.swivel.validator.validation_config.ValidEmailConfig
import com.swivel.validator.validation_config.ValidNameConfig
import com.swivel.validator.validation_config.ValidPasswordConfig
import javax.inject.Inject

/**
 * @author Yohan Dushmantha
 * @class LoginFormValidator
 */
class LoginFormValidator @Inject constructor(
    private val context : Context,
    private val baseValidator : BaseValidator
){
    /**
     * validate form data of login view model
     * @param  form data object of login
     * @return ValidationResult result of validation
     */
    fun validateFormData(
        loginFormData: LoginFormData?
    ) : ValidationResult {
        loginFormData?.let {
            val configResult : ArrayList<BaseValidationConfig> = ArrayList()

            configResult.add(ValidEmailConfig().apply {
                fieldName = context.getString(R.string.login_form_label_email).capitalize()
                text = it.email
                isRequired = true

            })

            configResult.add(ValidPasswordConfig().apply {
                fieldName = context.getString(R.string.login_form_label_password).capitalize()
                text = it.password
                isRequired = true
            })

            return baseValidator.validate(configResult,context)
        }

        throw FormDataNotFoundException()
    }
}