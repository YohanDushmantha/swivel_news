package com.swivel.models.features.shared.info_box.router_arguments

import com.swivel.models.features.IBaseDeepLinkArguments

/**
 * @author Yohan Dushmantha
 * @class InfoBoxDeepLinkArguments
 *
 * deepLink object for parsing data through deep link router
 *
 * @param infoBoxId id for identifying info box after triggering info box event
 * @param title title for info box
 * @param detail detail for info box
 * @param viewConfig view config object to config info box
 */
data class InfoBoxDeepLinkArguments constructor(
    var infoBoxId : Short = 0,
    var title : String? = null,
    var detail : String? = null,
    var viewConfig : InfoBoxViewConfig? = InfoBoxViewConfig()
) : IBaseDeepLinkArguments()

