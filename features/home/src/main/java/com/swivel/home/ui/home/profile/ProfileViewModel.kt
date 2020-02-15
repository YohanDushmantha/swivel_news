package com.swivel.home.ui.home.profile

import android.content.Context
import com.swivel.core.ui.BaseViewModel
import com.swivel.models.features.IBaseDeepLinkArguments
import com.swivel.models.features.home.profile.router_arguments.ProfileDeepLinkArguments
import com.swivel.navigation.router.Router
import javax.inject.Inject

/**
 * @author Yohan Dushmantha
 * @class ProfileViewModel
 */
class ProfileViewModel @Inject constructor(
    private val router : Router,
    private val context : Context
) : BaseViewModel(){

    private var receivedDeepLinkArgs : ProfileDeepLinkArguments? = null

    /**---------------------------------------------------------------------------------------------*
     * INIT - START
     *----------------------------------------------------------------------------------------------*/

    override fun initViewArguments(deepLinkArguments: IBaseDeepLinkArguments?) {
        (deepLinkArguments as? ProfileDeepLinkArguments)?.let {
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