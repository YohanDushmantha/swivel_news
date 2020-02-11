package com.swivel.models.features.shared.confirm_box.router_arguments

import com.swivel.models.features.shared.confirm_box.enums.ConfirmBoxButtonAppearance
import com.swivel.models.features.shared.confirm_box.event_listeners.ConfirmBoxEventResult

/**
 * @author Yohan Dushmantha
 * @class ConfirmBoxButtonConfig
 *
 * config object for configuring buttons of confirm box. this object consists of appearance details
 * and event details which is related to buttons
 *
 * @param callbackFunction callback function for triggering positive and negative button events
 * @param isVisible the status for handing visibility of the buttons. default value is true
 * @param buttonText text for displaying as a button title
 */
data class ConfirmBoxButtonConfig constructor(
    var callbackFunction : (confirmBoxID : Short, confirmBoxButtonAppearance : ConfirmBoxButtonAppearance, result : ConfirmBoxEventResult?) -> Unit,
    var isVisible : Boolean = true,
    var buttonText : String? = null

)