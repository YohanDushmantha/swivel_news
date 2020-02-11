package com.swivel.models.libs.navigation.enums

/**
 * @author Yohan Dushmantha
 * @enum DEEP_LINK
 *
 * All deep link urls should be added here as a DEEP_LINK Enum
 *
 * @param KEY String key for DEEP_LINK Enum
 * @param URL Deep Link URL for helping to navigate through DEEP_LINK , This url should be same
 * as navigation xml deep link url of fragments or dialogs
 */
enum class DEEP_LINK (val KEY : String ,val URL : String) {

    //SHARED MODULE
    SHARED_VERIFY_OTP("SHARED_VERIFY_OTP","shared://verify_otp"),
    SHARED_PROGRESS_BAR("SHARED_PROGRESS_BAR","shared://progress_bar"),
    SHARED_INFO_BOX("INFO_BOX","shared://info_box"),
    SHARED_CONFIRM_BOX("CONFIRM_BOX","shared://confirm_box"),
    SHARED_VERIFY_MOBILE_NUMBER("SHARED_VERIFY_MOBILE_NUMBER","shared://verify_mobile_number"),

    //ONBOARDING MODULE
    WALKTHROUGH("WALKTHROUGH","onboarding://walkthrough"),

    //USER REGISTRATION
    USER_REGISTRATION("USER_REGISTRATION","user_registration://user_registration"),

    //LOGIN MODULE
    LOGIN("LOGIN","login://login"),
    VERIFY_PASSWORD("VERIFY_PASSWORD","login://verify_password"),
    FORGOT_PASSWORD_OPTION("FORGOT_PASSWORD_OPTION","login://forgot_password_option"),

    //HOME MODULE
    HOME_MAIN("HOME_MAIN","home://main"),

    //POST MODULE
    POSTS_POSTS("POSTS_POSTS","posts://posts")

}