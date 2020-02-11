package com.swivel.ui.base.databindings

import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.BindingAdapter

/**
 * @author Yohan Dushmantha
 *
 * all kind of edit text binding should be added to this file
 */

/**
 * binding adapter for catching action done event of edit text fields
 * @param f ((textView : TextView) -> Unit)? callback method for return to focused or selected
 * text view when clicking ACTION_DONE, ACTION_GO or ACTION_SEND buttons
 */
@BindingAdapter("onEditorEnterAction")
fun EditText.onEditorEnterAction(f: ((textView : TextView) -> Unit)? ) {

    if (f == null) {
        setOnEditorActionListener(null)
    } else {
        setOnEditorActionListener { v, actionId, event ->

            val imeAction = when (actionId) {
                EditorInfo.IME_ACTION_DONE,
                EditorInfo.IME_ACTION_SEND,
                EditorInfo.IME_ACTION_GO -> true
                else -> false
            }

            val keyDownEvent = event?.keyCode == KeyEvent.KEYCODE_ENTER
                    && event.action == KeyEvent.ACTION_DOWN

            if (imeAction or keyDownEvent)
                true.also { f(v) }
            else false
        }
    }
}
