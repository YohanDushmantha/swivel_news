package com.swivel.ui.base.helpers.custom_dots_progress_bar_animator.exceptions

/**
 * @author Yohan Dushmantha
 * @class CustomDotsProgressBarAnimatorException
 *
 * CustomDotsProgressBarAnimatorException should be thrown when error occurred insdie CustomDotsProgressBarAnimator
 */
class CustomDotsProgressBarAnimatorException constructor(text : String? = null) : Exception() {
    override val message: String? = text ?: "Error Occured inside CustomDotsProgressBarAnimator"
}