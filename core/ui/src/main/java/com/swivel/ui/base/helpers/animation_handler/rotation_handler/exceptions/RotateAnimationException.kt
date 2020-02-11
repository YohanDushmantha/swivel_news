package com.swivel.ui.base.helpers.animation_handler.rotation_handler.exceptions

/**
 * @author Yohan Dushmantha
 * @class RotateAnimationException
 *
 * RotateAnimationException can be thrown when occurring some error inside rotate animation class
 */
class RotateAnimationException constructor(text : String? = null) : Exception(){
    override val message: String? = text ?: "Rotate Animation Exception"
}