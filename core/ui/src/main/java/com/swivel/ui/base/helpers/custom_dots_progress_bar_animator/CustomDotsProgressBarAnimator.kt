package com.swivel.ui.base.helpers.custom_dots_progress_bar_animator

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.view.View
import android.widget.ImageView
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

/**
 * @author Yohan Dushmantha
 * @class CustomDotsProgressBarAnimator
 *
 * animate custom dots progress bar
 */
class CustomDotsProgressBarAnimator @Inject constructor(
    private val context: Context
) : ICustomDotsProgressBarAnimator {

    private lateinit var finapDots : ArrayList<ImageView?>
    private var shortAnimationDuration : Int = 0
    private var currentAnimatedDot : Int = 0

    override fun setup(dots : ArrayList<ImageView?>, duration : Int?){
        this.finapDots  = dots
        shortAnimationDuration = duration ?: context.resources.getInteger(android.R.integer.config_shortAnimTime)
    }

    override fun start(){
        currentAnimatedDot = 0
        animate()
    }

    override fun stop(){
        try {
            finapDots[currentAnimatedDot]?.animation?.cancel()
            finapDots[currentAnimatedDot]?.clearAnimation()
            currentAnimatedDot = finapDots.size + 1
        }catch (ex : Exception){
            Timber.e(ex)
        }
    }

    private fun hideAll(){
        finapDots.forEach {
            it?.visibility = View.GONE
        }
    }


    private fun animate(){
        try {
            when(currentAnimatedDot){
                in 0 until finapDots.size - 1 -> {
                    finapDots[currentAnimatedDot]?.animate()
                    finapDots[currentAnimatedDot]?.animate()?.let {
                        it.alpha(1f)
                        it.duration = shortAnimationDuration.toLong()
                        it.setListener(object : AnimatorListenerAdapter(){
                            override fun onAnimationEnd(animation: Animator?) {
                                super.onAnimationEnd(animation)
                                this@CustomDotsProgressBarAnimator.shortAnimationDuration++
                            }
                        })
                    }?.start()
                    animate()
                }

                finapDots.size -> {
                    hideAll()
                }
            }
        }catch (ex : Exception){
            Timber.e(ex)
        }
    }

}