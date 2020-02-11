package com.swivel.ui.base.helpers.animation_handler.bounce_handler

import android.content.Context
import android.view.View
import android.view.animation.AnimationUtils
import com.swivel.ui.R
import com.swivel.ui.base.helpers.animation_handler.bounce_handler.exceptions.BounceAnimationException
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

/**
 * @author Yohan Dushmantha
 * @class BounceAnimationHandler
 *
 * Handle bounce animation
 */
class BounceAnimationHandler @Inject constructor(
    private val context: Context
) : IBounceAnimationHandler {

    /**
     * animate provided view with bouncy animation
     *
     * @param view target to be animated
     * @param amplitude max size of the view when animating
     * @param frequency number of occurences at given period
     */
    override fun animate(
        view : View?,
        amplitude : Double,
        frequency : Double
    ){
        view?.let {
            try {
                it.isClickable = false
                val animation = AnimationUtils.loadAnimation(context, R.anim.bounce)
                animation.interpolator = BounceInterpolator(amplitude,frequency)
                view.startAnimation(animation)
            }catch (ex : Exception){
                Timber.e(ex)
            }finally {
                it.isClickable = true
            }
        } ?: throw BounceAnimationException("Animation not found")
    }

}