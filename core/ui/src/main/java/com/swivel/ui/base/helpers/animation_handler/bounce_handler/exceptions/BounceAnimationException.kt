package com.swivel.ui.base.helpers.animation_handler.bounce_handler.exceptions

/**
 * @author Yohan Dushmantha
 * @class BounceAnimationException
 *
 * BounceAnimationException can be thrown when occurring some error inside bounce animation class
 */
class BounceAnimationException constructor(text : String? = null) : Exception(){
    override val message: String? = text ?: "Bounce Animation Exception"
}