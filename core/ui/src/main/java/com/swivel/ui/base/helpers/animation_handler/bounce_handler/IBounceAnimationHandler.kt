package com.swivel.ui.base.helpers.animation_handler.bounce_handler

import android.view.View

/**
 * @author Yohan Dushmantha
 * @class IBounceAnimationHandler
 *
 * provide interface for handling bounce animation
 */
interface IBounceAnimationHandler {
    /**
     * animate provided view with bouncy animation
     *
     * @param view target to be animated
     * @param amplitude max size of the view when animating
     * @param frequency number of occurences at given period
     */
    fun animate(
        view : View?,
        amplitude : Double = 0.05,
        frequency : Double = 5.0
    )
}