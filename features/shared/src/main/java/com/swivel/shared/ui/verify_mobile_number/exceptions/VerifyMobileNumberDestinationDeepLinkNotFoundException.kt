package com.swivel.shared.ui.verify_mobile_number.exceptions

/**
 * @author Yohan Dushmantha
 * @class VerifyMobileNumberDestinationDeepLinkNotFoundException
 *
 * when destination deep link not found , this can be triggered
 */
class VerifyMobileNumberDestinationDeepLinkNotFoundException : Exception() {
    override val message: String?
    get() = "Deep Link Not Found inside the Verify Mobile Number Destination"
}