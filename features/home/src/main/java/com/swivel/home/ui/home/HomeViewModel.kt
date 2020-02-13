package com.swivel.home.ui.home

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.swivel.core.ui.BaseViewModel
import com.swivel.models.features.IBaseDeepLinkArguments
import com.swivel.models.features.home.home.router_arguments.HomeDeepLinkArguments
import com.swivel.navigation.router.Router
import javax.inject.Inject

/**
 * @author Yohan Dushmantha
 * @class HomeViewModel
 */
class HomeViewModel @Inject constructor(
    private val router: Router,
    private val context: Context
) : BaseViewModel(){

    val isDrawerOpen : MutableLiveData<Boolean> = MutableLiveData()
    val receivedViewArguments : MutableLiveData<HomeDeepLinkArguments> = MutableLiveData()

    /**---------------------------------------------------------------------------------------------*
     * INIT - START
     *----------------------------------------------------------------------------------------------*/

    override fun initViewArguments(deepLinkArguments: IBaseDeepLinkArguments?) {
        deepLinkArguments?.let {
            receivedViewArguments.postValue(it as? HomeDeepLinkArguments)
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


    /**---------------------------------------------------------------------------------------------*
     * DATA HANDLING - START
     *----------------------------------------------------------------------------------------------*/

    /**---------------------------------------------------------------------------------------------*
     * DATA HANDLING - END
     *----------------------------------------------------------------------------------------------*/

}