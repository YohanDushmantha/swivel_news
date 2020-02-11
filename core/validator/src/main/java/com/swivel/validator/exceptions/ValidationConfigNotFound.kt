package com.swivel.validator.exceptions

import java.lang.Exception

/**
 * @author Yohan Dushmantha
 * @class ValidationConfigNotFound
 *
 * Dev should return this exception when not founding validation config
 */
class ValidationConfigNotFound : Exception() {
    override val message: String?
        get() = "Validation Config Not Found."
}