package com.swivel.registration.ui.user_registration.enums

/**
 * @author Yohan Dushmantha
 * @class UserRegistrationInfoBoxID
 *
 * @param infoBoxID info box id for identifying info box
 */
enum class UserRegistrationInfoBoxID (val infoBoxId: Short) {
    FORM_DATA_VALIDATION_ERROR(1),
    FORM_DATA_REGISTRATION_SUCCESS(2)
}