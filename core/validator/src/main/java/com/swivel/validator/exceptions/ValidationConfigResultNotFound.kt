package com.swivel.validator.exceptions

import java.lang.Exception

/**
 * @author Yohan Dushmantha
 * @class ValidationConfigResultNotFound
 *
 * Dev should return this exception when not founding validation config result
 */
class ValidationConfigResultNotFound : Exception(){
    override val message: String?
        get() = "Validation Config Result Not Found."
}