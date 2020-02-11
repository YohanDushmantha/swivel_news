package com.swivel.ui.base.helpers.custom_dots_progress_bar_animator

import android.widget.ImageView

/**
 * @author Yohan Dushmantha
 * @interface ICustomDotsProgressBarAnimator
 *
 * provide interface for CustomDotsProgressBarAnimator
 */
interface ICustomDotsProgressBarAnimator {
    fun setup(dots : ArrayList<ImageView?>, duration : Int? = null)
    fun start()
    fun stop()
}