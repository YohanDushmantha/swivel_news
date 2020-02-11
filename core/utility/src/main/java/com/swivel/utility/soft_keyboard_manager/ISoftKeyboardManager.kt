package com.swivel.utility.soft_keyboard_manager

import android.view.View

/**
 * @author Yohan Dushmantha
 * @class ISoftKeyboardManager
 *
 * provide interface for SoftKeyboardManager
 */
interface ISoftKeyboardManager {
    /**
     * hide soft keyboard
     * @param view view object
     */
    fun hideKeyboard(view: View?)
}