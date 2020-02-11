package com.swivel.ui.base.helpers.progress_bar_handler

import android.content.Context
import androidx.navigation.NavController
import com.swivel.models.features.shared.progress_bar.router_arguments.ProgressBarUIDeepLinkArguments
import com.swivel.models.libs.navigation.enums.DEEP_LINK
import com.swivel.navigation.router.Router
import com.swivel.ui.R
import com.swivel.ui.base.helpers.progress_bar_handler.exceptions.ProgressBarHandlerException
import javax.inject.Inject

/**
 * @author Yohan Dushmantha
 * @class ProgressBarHandler
 *
 * handle progress bar
 */
class ProgressBarHandler @Inject constructor(
    private val context: Context
) : IProgressBarHandler {

    /**---------------------------------------------------------------------------------------------*
     * COMMON UI METHODS - START
     *----------------------------------------------------------------------------------------------*/

    /**
     * toggle progress bar ui according to arguments that is provided
     * @param router instance of Router class for load progress dialog fragment
     * @param navController instance of NavController
     * @param visibility show or hide status for showing or hiding progress dialog fragment
     * @param currentPageId current page
     * @param loadingText a text for showing when progress bar is visible
     */
    override fun toggleProgressUI(
        router : Router?,
        navController: NavController?,
        visibility : Boolean,
        currentPageId : Int,
        loadingText : String?
    ){
        if(visibility){
            navController?.let {
                router?.route(
                    it,
                    DEEP_LINK.SHARED_PROGRESS_BAR,
                    null,
                    ProgressBarUIDeepLinkArguments().apply {
                        this.loadingText = loadingText ?: context.getString(R.string.progress_loading_bar_text)
                    }
                )
            } ?: throw ProgressBarHandlerException("Navigation controller not found.")

        }else{
            router?.dismissDialogFragment(navController,currentPageId,false)
        }
    }

    /**---------------------------------------------------------------------------------------------*
     * COMMON UI METHODS - END
     *----------------------------------------------------------------------------------------------*/
}