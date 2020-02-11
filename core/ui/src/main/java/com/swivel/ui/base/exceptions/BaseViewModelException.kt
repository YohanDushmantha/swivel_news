package com.swivel.ui.base.exceptions

class BaseViewModelException (text : String? = null) : Exception() {
    override val message: String? = text ?: "Base View Model Exception"
}