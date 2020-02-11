package com.swivel.ui.base.helpers.progress_bar_handler.exceptions

/**
 * @author Yohan Dushmantha
 * @class ProgressBarHandlerException
 *
 * ProgressBarHandlerException should be throw when error occurred inside progress bar handler
 */
class ProgressBarHandlerException constructor(text : String? = null) : Exception() {
    override val message: String? = text ?: "Exception occurred Inside progress bar handler"
}