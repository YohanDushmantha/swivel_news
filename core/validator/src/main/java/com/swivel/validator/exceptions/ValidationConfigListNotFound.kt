package com.swivel.validator.exceptions

import java.lang.Exception

/**
 * @author Yohan Dushmantha
 * @class ValidationConfigListNotFound
 *
 * Dev should return this exception when not founding validation config list
 */
class ValidationConfigListNotFound : Exception() {
    override val message: String?
        get() = "Validaiton Config List Not Found or zero items"
}