package com.swivel.models.features.shared.info_box.router_arguments
import android.view.Gravity
import com.swivel.models.features.shared.info_box.enums.InfoBoxAppearance

/**
 * @author Yohan Dushmantha
 * @class InfoBoxViewConfig
 *
 * view config object to config view of info box
 *
 * @param gravity gravity should be provided according to Gravity object
 * @param isAutoHide the status of AutoHide
 * @param visibleTime this should be provided by miliseconds
 * @param isHideWhenTouchingInfoBox hide Info Box when touch on visible info box
 * @param infoBoxType change message appearance according to MassageApperance
 * @param onTapInfoBoxCallback call back for tapping info box
 * @param isHideWhenTouchingOutsideOfInfoBox control hiding dialog when touching outside of the infobox
 * @param onTapInfoBoxOutsideCallback call back for tapping outside of info box
 */
data class InfoBoxViewConfig constructor(
    var gravity : Int = Gravity.TOP,
    var isAutoHide : Boolean = true,
    var visibleTime : Long = 10000,
    var isHideWhenTouchingInfoBox : Boolean = true,
    var infoBoxType: InfoBoxAppearance = InfoBoxAppearance.INFO,
    var onTapInfoBoxCallback : ((infoBoxId : Short) -> Unit)? = null,
    var isHideWhenTouchingOutsideOfInfoBox : Boolean = true
)