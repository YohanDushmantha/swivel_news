package com.swivel.models.features.home.news_detail.router_arguments

import com.swivel.models.entities.News
import com.swivel.models.features.IBaseDeepLinkArguments

/**
 * @author Yohan Dushmantha
 * @class NewsDetailDeepLinkArguments
 */
data class NewsDetailDeepLinkArguments constructor(
    val news : News? = null
) : IBaseDeepLinkArguments()