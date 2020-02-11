package com.swivel.models.libs.navigation

/**
 * @author Yohan Dushmantha
 * @class ValidationResult
 *
 * used as a DTO of validation result
 */
data class ValidationResult constructor(var isValid : Boolean, var message : String? = null)