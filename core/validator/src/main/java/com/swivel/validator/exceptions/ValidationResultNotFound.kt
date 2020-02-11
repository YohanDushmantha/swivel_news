package com.swivel.validator.exceptions

import java.lang.Exception

/**
 * @author Yohan Dushmantha
 * @class ValidationResultNotFound
 *
 * Dev should return this exception when not founding validation result
 */
class ValidationResultNotFound : Exception(){
    override val message: String?
        get() = "Validation Result Not Found."
}