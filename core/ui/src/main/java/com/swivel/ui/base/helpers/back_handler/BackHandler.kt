package com.swivel.ui.base.helpers.back_handler

import android.view.KeyEvent
import android.view.View
import javax.inject.Inject

/**
 * @author Yohan Dushmantha
 * @class BackHandler
 *
 * provides capability to handle identify back event of fragments
 */
class BackHandler @Inject constructor() : IBackHandler{

    /**=============================================================================================*
     * HANDLE BACK BUTTON BEHAVIOUR - START
     *==============================================================================================*/

    /**
     * bind back pressed listener for catching back hardware back event
     *
     * @param view target view
     * @param currentDestinationId
     */
    fun bindBackPressedListener(
        view : View?,
        currentDestinationId : Int,
        expectedRootId : Int,
        callbackWhenBackPressedInRoot : (currentDestinationId: Int) -> Unit
    ){
        view?.let {
            it.isFocusableInTouchMode = true
            it.requestFocus()
            it.setOnKeyListener { v, keyCode, event ->
                if (event.action == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    if(currentDestinationId == expectedRootId){
                        callbackWhenBackPressedInRoot(currentDestinationId)
                    }
                    true
                } else false
            }
        }
    }

    /**=============================================================================================*
     * HANDLE BACK BUTTON BEHAVIOUR - END
     *==============================================================================================*/
}