package com.swivel.home.ui.home.exceptions

/**
 * @author Yohan Dushmantha
 * @class HomeViewModelException
 *
 * HomeViewModelException can be thrown when error occurred inside HomeViewModel
 */
class HomeViewModelException constructor(text : String? = null) : Exception() {
    override val message: String? = text ?: "Home ViewModel Exception"
}