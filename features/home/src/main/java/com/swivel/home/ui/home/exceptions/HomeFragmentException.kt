package com.swivel.home.ui.home.exceptions

/**
 * @author Yohan Dushmantha
 * @class HomeFragmentException
 *
 * HomeFragmentException can be thrown when error occurred inside HomeFragment
 */
class HomeFragmentException constructor(text : String? = null) : Exception() {
    override val message: String? = text ?: "Home Fragment Exception"
}