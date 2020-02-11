package com.swivel.ui.base

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.InsetDrawable
import android.view.WindowManager
import androidx.navigation.NavController
import com.swivel.models.features.shared.progress_bar.router_arguments.ProgressBarUIDeepLinkArguments
import com.swivel.models.libs.navigation.enums.DEEP_LINK
import com.swivel.navigation.router.Router
import dagger.android.support.DaggerDialogFragment

/**
 * @author Yohan Dushmantha
 * @class BaseDialogFragment
 *
 * BaseDialogFragment should be implemented by every dialog fragment
 *
 * All kind of common methods which is related to dialog fragments should be added here
 *
 * provides abstract methods for dialog fragments to protect consistency, readability , and maintainability
 * of the code
 *
 */
abstract class BaseDialogFragment : DaggerDialogFragment() {

    /**---------------------------------------------------------------------------------------------*
     * LIFECYCLE METHODS - START
     *----------------------------------------------------------------------------------------------*/

    override fun onStart() {
        super.onStart()
        increaseWidthToFitWindow()
    }

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
     * PREPARE DIALOG FRAGMENT ACCORDING TO THEME - START
     *----------------------------------------------------------------------------------------------*/

    /**
     * remove unnecessary margins of dialog fragment
     */
    private fun increaseWidthToFitWindow(){
        dialog?.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
    }

    /**
     * add insetDrawable to remove left and right side margins of dialog fragment
     */
    fun removefixHorizontalMarginsOfDialogFragment(){
        val back = ColorDrawable(Color.TRANSPARENT)
        val inset = InsetDrawable(back, 16)
        dialog?.window?.setBackgroundDrawable(inset)
    }

    /**---------------------------------------------------------------------------------------------*
     * PREPARE DIALOG FRAGMENT ACCORDING TO THEME - END
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