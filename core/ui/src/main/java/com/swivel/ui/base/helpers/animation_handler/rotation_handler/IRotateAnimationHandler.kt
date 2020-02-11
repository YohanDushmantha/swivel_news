package com.swivel.ui.base.helpers.animation_handler.rotation_handler

import android.view.View

/**
 * @author Yohan Dushmantha
 * @class IRotateAnimationHandler
 *
 * provide interface for RotateAnimationHandler
 */
interface IRotateAnimationHandler {

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
    fun animate(
        view: View?,
        toDegrees: Float = 360f,
        fromDegrees: Float = 0f,
        duration: Long = 300,
        reverse: Boolean = false
    )
}