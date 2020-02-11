package com.swivel.ui.base.helpers.animation_handler.bounce_handler

import android.view.animation.Interpolator

/**
 * @author Yohan Dushmantha
 * @class BounceInterpolator
 *
 * Bounce Interpolator
 */
class BounceInterpolator constructor(
    private var amplitude : Double,
    private var frequence : Double
) : Interpolator {

    override fun getInterpolation(time : Float): Float {
        return (-1 * Math.pow(Math.E, -time/ amplitude) * Math.cos(frequence * time) + 1).toFloat()
    }
}