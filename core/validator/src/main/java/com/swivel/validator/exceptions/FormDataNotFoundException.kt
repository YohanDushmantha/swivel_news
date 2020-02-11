package com.swivel.validator.exceptions

import java.lang.Exception

/**
 * @author Yohan Dushmantha
 * @class FormDataNotFoundException
 *
 * Dev should return this exception when not founding form data
 */
class FormDataNotFoundException : Exception() {
    override val message: String?
        get() = "Form Data Not Found. Form Data should be not null object"
}