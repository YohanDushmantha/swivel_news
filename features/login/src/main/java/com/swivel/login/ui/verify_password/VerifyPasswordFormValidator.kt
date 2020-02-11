package com.swivel.login.ui.verify_password

import android.content.Context
import com.swivel.config.constants.AppStates
import com.swivel.validator.BaseValidator
import com.swivel.models.libs.navigation.ValidationResult
import com.swivel.validator.exceptions.FormDataNotFoundException
import com.swivel.validator.validation_config.BaseValidationConfig
import com.swivel.validator.validation_config.ValidPasswordConfig
import javax.inject.Inject

/**
 * @author Yohan Dushmantha
 * @class VerifyPasswordFormValidator
 */
class VerifyPasswordFormValidator @Inject constructor(
    private val context: Context,
    private val baseValidator : BaseValidator,
    private val appStates: AppStates
) {

    /**
     * validate form data of verifyPassword view model
     * @param  form data object of post
     * @return ValidationResult result of validation
     */
    fun validateFormData(
        verifyPasswordFormData: VerifyPasswordFormData?
    ) : ValidationResult {
        verifyPasswordFormData?.let {
            val configResult : ArrayList<BaseValidationConfig> = ArrayList()

            configResult.add(ValidPasswordConfig().apply {
                text = it.password
                isRequired = true
                passwordPolicy = appStates.systemConfiguration?.PasswordPolicy
                passwordPolicyHint = appStates.systemConfiguration?.PasswordPolicy
            })

            return baseValidator.validate(configResult,context)
        }

        throw FormDataNotFoundException()
    }
}