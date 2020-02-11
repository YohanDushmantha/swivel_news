package com.swivel.ui.base.helpers.info_box_handler.exceptions

import java.lang.Exception

/**
 * @author Yohan Dushmantha
 * @class InfoBoxHandlerException
 * this exception can be thrown when something happend inside Info box handler
 */
class InfoBoxHandlerException (text : String? = null) : Exception() {

    override val message: String? = text ?: "Info Box Handler Exception"
}