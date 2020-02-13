package com.swivel.ui.base.helpers.info_box_handler

import androidx.navigation.NavController
import com.swivel.models.libs.navigation.ValidationResult
import com.swivel.navigation.router.Router

/**
 * @author Yohan Dushmantha
 * @class IInfoBoxHandler
 * provide interface to InfoBoxHandler
 */
interface IInfoBoxHandler {

    /**
     * show error message which is related to provided validation result
     * @param router router
     * @param navController navigation controller
     * @param validationResult validation result object which is provided by validator
     * @param infoBoxIdentifier id for identifying info box when tapping
     * @param onTapCallback callback for on tap event
     */
    fun showErrorInfoBox(
        router : Router,
        navController: NavController?,
        validationResult : ValidationResult,
        infoBoxIdentifier : Short,
        onTapCallback : ((infoBoxId : Short) -> Unit)? = null,
        isAutoHide : Boolean = false
    )

    /**
     * show error message which is related to provided validation result
     * @param router router
     * @param navController navigation controller
     * @param validationResult validation result object which is provided by validator
     * @param infoBoxIdentifier id for identifying info box when tapping
     * @param onTapCallback callback for on tap event
     */
    fun showErrorInfoBox(
        router : Router,
        navController: NavController?,
        message : String?,
        infoBoxIdentifier : Short,
        onTapCallback : ((infoBoxId : Short) -> Unit)? = null
    )
}