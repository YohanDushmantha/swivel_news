package com.swivel.ui.base.helpers.drawer_handler

import android.content.Context
import androidx.appcompat.app.ActionBar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import com.swivel.models.libs.navigation.enums.DrawerConfigSettings
import com.swivel.ui.base.helpers.drawer_handler.exceptions.DrawerHandlerException
import timber.log.Timber
import javax.inject.Inject


/**
 * @author Yohan Dushmantha
 * @class DrawerHandler
 * handle drawer related configurations
 * basically this class provides capability to show and hide drawer and also show and hide
 * tool bar
 */
class DrawerHandler @Inject constructor(
    private val context: Context
) : IDrawerHandler {

    private var actionBar: ActionBar? = null
    private var drawerLayout: DrawerLayout? = null
    private var updateDrawerData : (() -> Unit)? = null

    /**---------------------------------------------------------------------------------------------*
     * INIT - START
     *----------------------------------------------------------------------------------------------*/

    /**
     * initialize app bar and drawer layout
     *
     * @param actionBar action bar to show and hide
     * @param drawerLayout drawer Layout to enable and disable gestures
     */
    override fun initialize(
        actionBar: ActionBar?,
        drawerLayout: DrawerLayout?,
        updateDrawerData :  (() -> Unit)?
    ){
        actionBar?.let {
            this@DrawerHandler.actionBar = it
        } ?: throw DrawerHandlerException("Action bar not found.")

        drawerLayout?.let {
            this@DrawerHandler.drawerLayout = it
        } ?: throw DrawerHandlerException("Drawer layout not found.")

        updateDrawerData?.let {
            this@DrawerHandler.updateDrawerData = it
        } ?: throw DrawerHandlerException("Drawer Update Callback not found")
    }

    /**---------------------------------------------------------------------------------------------*
     * INIT - END
     *----------------------------------------------------------------------------------------------*/

    /**---------------------------------------------------------------------------------------------*
     * SETUP - START
     *----------------------------------------------------------------------------------------------*/

    /**
     * setup drawer and app bar according to provided configuration settings
     *
     * @param navController navigation controller if navigation controller is empty should thrown
     * DrawerHandlerException
     * @param drawerConfigSettings action bar and drawer layout should be customized according to
     * drawerConfigSettings
     */
    override fun setup(
        navController: NavController?,
        drawerConfigSettings: DrawerConfigSettings
    ) {
        try {
            navController?.let {
                setupDrawer(drawerConfigSettings.isEnableDrawerGesture,drawerLayout)
                setupAppBar(drawerConfigSettings.isVisibleAppBar,actionBar)
                updateDrawerData?.let { callback -> callback() }

            } ?: throw DrawerHandlerException("Navigation controller not found.")

        }catch (ex : Exception){
            Timber.e(ex)
        }
    }

    /**
     * setup drawer
     *
     * @param isEnabled status of enabling and disabling drawer gestures
     * @param drawerLayout drawer layout for applying changes
     */
    private fun setupDrawer(isEnabled : Boolean, drawerLayout: DrawerLayout? = null){
        drawerLayout?.let {
            if(isEnabled){
                it.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
            }else{
                it.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            }
        }
    }

    /**
     * setup app bar
     *
     * @param isVisible status of visibility of appbar
     * @param actionBar action bar for applying changes
     */
    private fun setupAppBar(isVisible : Boolean, actionBar : ActionBar? = null){
        actionBar?.let {
            if(isVisible){
                //initCustomAppBar("Verify")
                it.show()
            }else{
                it.hide()
            }
        }
    }

    /**---------------------------------------------------------------------------------------------*
     * SETUP - END
     *----------------------------------------------------------------------------------------------*/

    /**---------------------------------------------------------------------------------------------*
     * DRAWER VISIBILITY - START
     *----------------------------------------------------------------------------------------------*/


    override fun showDrawer() {
        drawerLayout?.let{
            it.openDrawer(GravityCompat.START)
        } ?: throw DrawerHandlerException("Drawer not found.")

    }

    override fun hideDrawer() {
        drawerLayout?.let {
            it.closeDrawer(GravityCompat.END)
        }?: throw DrawerHandlerException("Drawer not found.")

    }

    /**---------------------------------------------------------------------------------------------*
     * DRAWER VISIBILITY - END
     *----------------------------------------------------------------------------------------------*/

    fun initCustomAppBar(title : String? = null){
        try {
            actionBar?.let {actionBar ->
                /*val titleId: Int = context.resources.getIdentifier("action_bar_title", "id", "android")

                 drawerLayout?.findViewById<TextView>(titleId)?.let {
                    it.setTextColor(ContextCompat.getColor(context, R.color.action_bar_text))

                    val metrics = DisplayMetrics()
                    (context.getSystemService(Context.WINDOW_SERVICE) as? WindowManager)?.let { wm ->
                        wm.defaultDisplay.getMetrics(metrics)
                    } ?: throw DrawerHandlerException("Window Manager not found.")

                    it.gravity = Gravity.CENTER
                    it.width = metrics.widthPixels
                    actionBar.title = "Here it works"
                } ?: throw DrawerHandlerException("Text view not found.")*/
            } ?: throw DrawerHandlerException("Action Bar not initialized.")
        }catch (ex : Exception){
            Timber.e(ex)
        }
    }

    /**---------------------------------------------------------------------------------------------*
     * SETUP DRIVER DETAILS - END
     *----------------------------------------------------------------------------------------------*/
}