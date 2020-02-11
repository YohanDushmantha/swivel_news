package com.swivel.models.features.shared.confirm_box.router_arguments
import com.swivel.models.features.IBaseDeepLinkArguments

/**
 * @author Yohan Dushmantha
 * @class ConfirmBoxDeepLinkArguments
 *
 * deepLink object for parsing data through deep link router
 *
 * @param confirmBoxId id for identifying confirm box after triggering confirm box event
 * @param title title for confirm box
 * @param detail detail for confirm box
 * @param viewConfig view config object to config confirm box
 */
class ConfirmBoxDeepLinkArguments constructor(
    var confirmBoxId : Short = 0,
    var title : String? = null,
    var detail : String? = null,
    var viewConfig : ConfirmBoxViewConfig? = ConfirmBoxViewConfig()
): IBaseDeepLinkArguments()