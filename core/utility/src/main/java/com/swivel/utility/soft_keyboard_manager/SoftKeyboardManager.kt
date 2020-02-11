package com.swivel.utility.soft_keyboard_manager

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import javax.inject.Inject

/**
 * @author Yohan Dushmantha
 * @class SoftKeyboardManager
 *
 * provide capability to handle soft keyboard visibility and hidden status and etc.
 */
class SoftKeyboardManager @Inject constructor(
    private val context: Context?
) : ISoftKeyboardManager {

    override fun hideKeyboard(view: View?) {
        val inputMethodManager : InputMethodManager? = context?.getSystemService(Activity.INPUT_METHOD_SERVICE)
                as? InputMethodManager
        inputMethodManager?.hideSoftInputFromWindow(view?.windowToken,0)
        view?.clearFocus()
    }

}