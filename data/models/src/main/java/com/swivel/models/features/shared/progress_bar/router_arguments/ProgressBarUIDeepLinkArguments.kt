package com.swivel.models.features.shared.progress_bar.router_arguments

import com.swivel.models.features.IBaseDeepLinkArguments

/**
 * @author Yohan Dushmantha
 * @class ProgressBarUIDeepLinkArguments
 *
 * deepLink object for parsing data through deep link router
 *
 * @param loadingText text for display as a loading text
 */
class ProgressBarUIDeepLinkArguments constructor(
    var loadingText : String? = "Loading"
) : IBaseDeepLinkArguments()