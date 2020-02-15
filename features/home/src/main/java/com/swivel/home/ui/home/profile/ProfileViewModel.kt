package com.swivel.home.ui.home.profile

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.swivel.config.constants.AppStates
import com.swivel.core.ui.BaseViewModel
import com.swivel.models.entities.UserAuthentication
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
    private val context : Context,
    private val appStates: AppStates
) : BaseViewModel(){

    private var receivedDeepLinkArgs : ProfileDeepLinkArguments? = null
    var authentication : MutableLiveData<UserAuthentication> = MutableLiveData()

    /**---------------------------------------------------------------------------------------------*
     * INIT - START
     *----------------------------------------------------------------------------------------------*/

    override fun initViewArguments(deepLinkArguments: IBaseDeepLinkArguments?) {
        (deepLinkArguments as? ProfileDeepLinkArguments)?.let {
            receivedDeepLinkArgs = it
        }
        appStates.userAuth?.let{
            authentication.postValue(it)
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