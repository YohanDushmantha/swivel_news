package com.swivel.ui.base.helpers.animation_handler.rotation_handler

import android.content.Context
import android.view.View
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import com.swivel.ui.base.helpers.animation_handler.rotation_handler.exceptions.RotateAnimationException
import timber.log.Timber
import javax.inject.Inject


/**
 * @author Yohan Dushmantha
 * @class FabAnimationHandler
 *
 * handler Floating Action Button animations
 */
class RotateAnimationHandler @Inject constructor(
    private val context: Context
) : IRotateAnimationHandler {

    /**
     * rotate provided view according to provided values
     *
     * @param view target to be rotated
     * @param toDegrees ending angle of rotation
     * @param fromDegrees starting angle of rotation
     * @param duration duration for animation
     * @param reverse status of reverse rotation, if reverse is true should be rotate icon to starting
     * position otherwise keep it in rotated angle
     */
    override fun animate(
        view: View?,
        toDegrees: Float,
        fromDegrees: Float,
        duration: Long,
        reverse: Boolean
    ) {

        try {
            view?.let {
                val rotateAnimation = RotateAnimation(
                    fromDegrees, toDegrees,
                    Animation.RELATIVE_TO_SELF,
                    0.5f,
                    Animation.RELATIVE_TO_SELF,
                    0.5f
                )

                rotateAnimation.duration = duration
                rotateAnimation.fillAfter = true
                it.startAnimation(rotateAnimation)
            } ?: throw RotateAnimationException("Target not found")
        } catch (ex: Exception) {
            Timber.e(ex)
        }

    }

}