package com.swivel.ui.base.helpers.progress_bar_handler

import androidx.navigation.NavController
import com.swivel.navigation.router.Router
/**
 * @author Yohan Dushmantha
 * @class IProgressBarHandler
 *
 * provide interface to handle progress bar
 */
interface IProgressBarHandler {

    /**
     * toggle progress bar ui according to arguments that is provided
     * @param router instance of Router class for load progress dialog fragment
     * @param navController instance of NavController
     * @param visibility show or hide status for showing or hiding progress dialog fragment
     * @param currentPageId current page
     * @param loadingText a text for showing when progress bar is visible
     */
    fun toggleProgressUI(
        router : Router?,
        navController: NavController?,
        visibility : Boolean,
        currentPageId : Int,
        loadingText : String? = null
    )

}