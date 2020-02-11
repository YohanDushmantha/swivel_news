package com.swivel.shared_pref.exceptions

import java.lang.Exception

/**
 * @author Yohan Dushmantha
 * @class SharedPreferencesPushException
 *
 * developer should throw SharedPreferencesPushException object when occurring some issue in pushing
 * data into shared preferences
 */
class SharedPreferencesPushException : Exception() {
    override val message: String = "Error in saving data into shared preferences."
}