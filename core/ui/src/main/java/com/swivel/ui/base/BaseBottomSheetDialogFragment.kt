package com.swivel.ui.base

import androidx.navigation.NavController
import com.swivel.config.di.injectors.DaggerBottomSheetDialogFragment
import com.swivel.models.features.shared.progress_bar.router_arguments.ProgressBarUIDeepLinkArguments
import com.swivel.models.libs.navigation.enums.DEEP_LINK
import com.swivel.navigation.router.Router

/**
 * @author Yohan Dushmantha
 * @class BaseBottomSheetDialogFragment
 *
 * BaseBottomSheetDialogFragment should be implemented by every bottom sheet dialog fragment
 *
 * All kind of common methods which is related to bottom sheet dialog fragments should be added here
 *
 * provides abstract methods for bottom sheet dialog fragments to protect consistency, readability , and maintainability
 * of the code
 *
 */

abstract class BaseBottomSheetDialogFragment : DaggerBottomSheetDialogFragment() {

    //@Inject lateinit var dummy: DummyInjectableField

    /**---------------------------------------------------------------------------------------------*
     * LIFECYCLE METHODS - START
     *----------------------------------------------------------------------------------------------*/


    /**---------------------------------------------------------------------------------------------*
     * LIFECYCLE METHODS - END
     *----------------------------------------------------------------------------------------------*/


    /**---------------------------------------------------------------------------------------------*
     * INIT - START
     *----------------------------------------------------------------------------------------------*/

    /**
     * all observers should be implemented here
     */
    abstract fun initObservers()

    /**
     * progress bar should be implemented here
     */
    abstract fun initLoader()

    /**
     * progress bar should be implemented here
     */
    abstract fun initViews()

    /**---------------------------------------------------------------------------------------------*
     * INIT - END
     *----------------------------------------------------------------------------------------------*/

    /**---------------------------------------------------------------------------------------------*
     * COMMON UI METHODS - START
     *----------------------------------------------------------------------------------------------*/

    /**
     * toggle progress bar ui according to arguments that is provided
     * @param router instance of Router class for load progress dialog fragment
     * @param navController instance of NavController
     * @param visibility show or hide status for showing or hiding progress dialog fragment
     * @param loadingText a text for showing when progress bar is visible
     */
    fun toggleProgressUI(router : Router?, navController: NavController?, visibility : Boolean, loadingText : String? = "Loading"){
        if(visibility){
            router?.route(navController!!,
                DEEP_LINK.SHARED_PROGRESS_BAR,null, ProgressBarUIDeepLinkArguments().apply {
                this.loadingText = loadingText
            })
        }else{
            router?.routeBack(navController!!)
        }
    }

    /**---------------------------------------------------------------------------------------------*
     * COMMON UI METHODS - END
     *----------------------------------------------------------------------------------------------*/
}