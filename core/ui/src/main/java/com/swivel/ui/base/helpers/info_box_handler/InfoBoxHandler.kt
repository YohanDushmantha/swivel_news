package com.swivel.ui.base.helpers.info_box_handler

import androidx.navigation.NavController
import com.swivel.models.features.shared.info_box.enums.InfoBoxAppearance
import com.swivel.models.features.shared.info_box.router_arguments.InfoBoxDeepLinkArguments
import com.swivel.models.features.shared.info_box.router_arguments.InfoBoxViewConfig
import com.swivel.models.libs.navigation.ValidationResult
import com.swivel.models.libs.navigation.enums.DEEP_LINK
import com.swivel.navigation.router.Router
import com.swivel.ui.base.exceptions.BaseViewModelException
import javax.inject.Inject

/**
 * @author Yohan Dushmantha
 * @class InfoBoxHandler
 * show info box messages according to provided data
 */
class InfoBoxHandler @Inject constructor() : IInfoBoxHandler {


    /**---------------------------------------------------------------------------------------------*
     * SHOW ERROR INFO BOX - START
     *----------------------------------------------------------------------------------------------*/

    /**
     * show error message which is related to provided validation result
     * @param router router
     * @param navController navigation controller
     * @param validationResult validation result object which is provided by validator
     * @param infoBoxIdentifier id for identifying info box when tapping
     * @param onTapCallback callback for on tap event
     */
    override fun showErrorInfoBox(
        router : Router,
        navController: NavController?,
        validationResult : ValidationResult,
        infoBoxIdentifier : Short,
        onTapCallback : ((infoBoxId : Short) -> Unit)?,
        isAutoHide : Boolean
    ){
        navController?.let {
            router.route(
                navController,
                DEEP_LINK.SHARED_INFO_BOX,
                null,
                InfoBoxDeepLinkArguments().apply {
                    infoBoxId = infoBoxIdentifier
                    detail = validationResult.message
                    viewConfig = InfoBoxViewConfig().apply {
                        infoBoxType = InfoBoxAppearance.ERROR
                        onTapInfoBoxCallback = onTapCallback
                        this.isAutoHide = isAutoHide
                    }
                }
            )
        } ?: throw BaseViewModelException("Nav Controller Not found when showing error info box")
    }

    /**
     * show error message which is related to provided validation result
     * @param router router
     * @param navController navigation controller
     * @param validationResult validation result object which is provided by validator
     * @param infoBoxIdentifier id for identifying info box when tapping
     * @param onTapCallback callback for on tap event
     */
    override fun showErrorInfoBox(
        router : Router,
        navController: NavController?,
        message : String?,
        infoBoxIdentifier : Short,
        onTapCallback : ((infoBoxId : Short) -> Unit)?
    ){
        navController?.let {
            router.route(
                navController,
                DEEP_LINK.SHARED_INFO_BOX,
                null,
                InfoBoxDeepLinkArguments().apply {
                    infoBoxId = infoBoxIdentifier
                    detail = message
                    viewConfig = InfoBoxViewConfig().apply {
                        infoBoxType = InfoBoxAppearance.ERROR
                        onTapInfoBoxCallback = onTapCallback
                    }
                }
            )
        } ?: throw BaseViewModelException("Nav Controller Not found when showing error info box")
    }

    /**---------------------------------------------------------------------------------------------*
     * SHOW ERROR INFO BOX - END
     *----------------------------------------------------------------------------------------------*/

}