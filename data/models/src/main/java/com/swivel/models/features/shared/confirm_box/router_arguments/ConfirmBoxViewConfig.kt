package com.swivel.models.features.shared.confirm_box.router_arguments

import com.swivel.models.features.shared.confirm_box.enums.ConfirmBoxAppearance

/**
 * @author Yohan Dushmantha
 * @class ConfirmBoxViewConfig
 *
 * view config object to config view of confirm box
 *
 * @param isCancellable  status for hiding confirm box view when touching outside of the confirm
 * box or clicking back button
 * @param confirmBoxAppearance confirm box appearance will be changed according to confirm box
 * appearance status
 * @param buttonList config button list for config confirm box positive and negative buttons
 */
data class ConfirmBoxViewConfig constructor(
    var isCancellable : Boolean = false,
    var confirmBoxAppearance: ConfirmBoxAppearance = ConfirmBoxAppearance.DEFAULT,
    var buttonList : HashMap<Int, ConfirmBoxButtonConfig>? = null
)