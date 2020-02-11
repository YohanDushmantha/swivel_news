package com.swivel.shared.ui.verify_otp

import android.view.View
import androidx.navigation.findNavController
import com.swivel.core.ui.BaseViewModel
import com.swivel.models.features.IBaseDeepLinkArguments
import com.swivel.models.features.home.home.router_arguments.HomeDeepLinkArguments
import com.swivel.models.libs.navigation.enums.DEEP_LINK
import com.swivel.navigation.router.Router
import javax.inject.Inject

/**
 * @author Yohan Dushmantha
 * @class VerifyOtpViewModel
 */

class VerifyOtpViewModel @Inject constructor(private val router: Router) : BaseViewModel() {

    /**---------------------------------------------------------------------------------------------*
     * INIT - START
     *----------------------------------------------------------------------------------------------*/

    override fun initViewArguments(deepLinkArguments: IBaseDeepLinkArguments?) {

    }

    /**---------------------------------------------------------------------------------------------*
     * INIT - END
     *----------------------------------------------------------------------------------------------*/

    /**----------------------------------------------------------------------------------------------------------------*
     * EVENTS HANDLING - START
     *-----------------------------------------------------------------------------------------------------------------*/

    fun onTapResend(view: View?){
        view?.let{

        }
    }

    fun onTapVerify(view : View?){
        view?.let {
            router.route(view.findNavController(),DEEP_LINK.HOME_MAIN,null,HomeDeepLinkArguments().apply {

            })
        }
    }

    /**----------------------------------------------------------------------------------------------------------------*
     * EVENTS HANDLING - END
     *-----------------------------------------------------------------------------------------------------------------*/

}