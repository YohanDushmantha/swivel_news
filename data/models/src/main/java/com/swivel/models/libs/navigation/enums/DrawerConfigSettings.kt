package com.swivel.models.libs.navigation.enums

/**
 * @author Yohan Dushmantha
 * @enum DrawerConfigSettings
 *
 * drawer configuration settings for config drawer
 */
enum class DrawerConfigSettings (
    val isEnableDrawerGesture: Boolean,
    val isVisibleAppBar : Boolean
) {
    ONLY_SHOW_APP_BAR(false,true),
    ONLY_ENABLE_DRAWER_GESTURE(true,false),
    ADD_ALL(true,true),
    REMOVE_ALL(false,false)
}