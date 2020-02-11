package com.swivel.ui.base.helpers.drawer_handler

import androidx.appcompat.app.ActionBar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import com.swivel.models.libs.navigation.enums.DrawerConfigSettings

/**
 * @author Yohan Dushmantha
 * @class DrawerHandler
 * provide interface for DrawerHandler
 */
interface IDrawerHandler  {

    /**
     * initialize app bar and drawer layout
     *
     * @param actionBar action bar to show and hide
     * @param drawerLayout drawer Layout to enable and disable gestures
     */
    fun initialize(
        actionBar: ActionBar?,
        drawerLayout: DrawerLayout?,
        updateDrawerData :  (() -> Unit)?
    )

    /**
     * setup drawer and app bar according to provided configuration settings
     *
     * @param navController navigation controller
     * @param drawerConfigSettings action bar and drawer layout should be customized according to
     * drawerConfigSettings
     */
    fun setup(
        navController: NavController? = null,
        drawerConfigSettings: DrawerConfigSettings = DrawerConfigSettings.ONLY_SHOW_APP_BAR
    )

    fun showDrawer()

    fun hideDrawer()
}