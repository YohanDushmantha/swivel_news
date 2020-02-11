package com.swivel.ui.base.helpers.drawer_handler.exceptions

/**
 * @author Yohan Dushmantha
 * @class DrawerHandlerException
 *
 * This exception should be thrown when error occurred inside DrawerHandler class
 */
class DrawerHandlerException constructor(text : String? = null) : Exception() {
    override val message: String = text ?: "Drawer Handler Exception"
}