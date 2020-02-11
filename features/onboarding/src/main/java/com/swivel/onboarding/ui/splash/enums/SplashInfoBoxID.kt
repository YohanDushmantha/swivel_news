package com.swivel.onboarding.ui.splash.enums

/**
 * @author Yohan Dushmantha
 * @class SplashInfoBoxID
 *
 * consists of info box message id's of splash fragment. this is for preventing duplication of info
 * box callbacks.
 *
 * @param infoBoxID info box id for identifying info box
 */
enum class SplashInfoBoxID (val infoBoxID : Short) {
    TASK_PROGRESS_ERROR_KEYSTORE_GENERATION_FAILURE(1),
    TASK_PROGRESS_ERROR_DEVICE_IS_ROOTED(2),
    TASK_PROGRESS_ERROR_DENIED_PHONE_STATE_PERMISSION(3),
    TASK_PROGRESS_ERROR_DENIED_ACCESS_NETWORK_STATE_PERMISSION(4),
    TASK_PROGRESS_ERROR_DEVICE_INFO_NOT_LOADED(5),
    TASK_PROGRESS_ERROR_SYSTEM_CONFIGURATION_NOT_LOADED(6)

}