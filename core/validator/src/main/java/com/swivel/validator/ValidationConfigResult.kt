package com.swivel.validator

/**
 * @author Yohan Dushmantha
 * @class ValidationConfigResult
 *
 * this object behave as a DTO for transfer Validation result from ValidationConfig to BaseValidator
 */
class ValidationConfigResult(

    //validation status
    var isValid : Boolean,

    // string resource id
    var resourceId : Int? = null,

    // required status of success message, if this attribute is true, should be return success
    // message with validation result
    var isSuccessMessageRequired : Boolean = false,

    // custom error message
    var customErrorMessage : String? = null,

    // custom success message
    var customSuccessMessage : String? = null,

    //argument list for format strings
    vararg var argumentList : Any?
)