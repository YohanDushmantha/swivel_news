package com.swivel.home.ui.filtered_news

import android.content.Context
import com.swivel.core.ui.BaseViewModel
import com.swivel.models.features.IBaseDeepLinkArguments
import com.swivel.models.features.home.filtered_news.router_arguments.FilteredNewsDeepLinkArguments
import com.swivel.navigation.router.Router
import javax.inject.Inject

/**
 * @author Yohan Dushmantha
 * @class FilteredNewsViewModel
 */
class FilteredNewsViewModel @Inject constructor(
    private val router : Router,
    private val context : Context
) : BaseViewModel(){

    private var receivedDeepLinkArgs : FilteredNewsDeepLinkArguments? = null

    /**---------------------------------------------------------------------------------------------*
     * INIT - START
     *----------------------------------------------------------------------------------------------*/

    override fun initViewArguments(deepLinkArguments: IBaseDeepLinkArguments?) {
        (deepLinkArguments as? FilteredNewsDeepLinkArguments)?.let {
            receivedDeepLinkArgs = it
        }
    }

    /**---------------------------------------------------------------------------------------------*
     * INIT - END
     *----------------------------------------------------------------------------------------------*/

    /**---------------------------------------------------------------------------------------------*
     * EVENTS HANDLING - START
     *----------------------------------------------------------------------------------------------*/



    /**---------------------------------------------------------------------------------------------*
     * EVENTS HANDLING - END
     *----------------------------------------------------------------------------------------------*/

}