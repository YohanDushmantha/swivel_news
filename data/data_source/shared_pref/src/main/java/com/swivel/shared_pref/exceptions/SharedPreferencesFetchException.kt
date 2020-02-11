package com.swivel.shared_pref.exceptions

import java.lang.Exception

/**
 * @author Yohan Dushmantha
 * @class SharedPreferencesFetchException
 *
 * developer should throw SharedPreferencesFetchException object when occurring some issue in fetching
 * data from shared preferences
 */
class SharedPreferencesFetchException : Exception() {
    override val message: String = "Error in fetching data from shared preferences"
}