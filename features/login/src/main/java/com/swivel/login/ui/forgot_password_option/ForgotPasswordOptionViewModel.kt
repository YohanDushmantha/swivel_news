package com.swivel.login.ui.forgot_password_option

import android.view.View
import androidx.navigation.findNavController
import com.swivel.core.ui.BaseViewModel
import com.swivel.models.features.IBaseDeepLinkArguments
import com.swivel.models.features.shared.verify_otp.router_arguments.VerifyOTPDeepLinkArguments
import com.swivel.models.libs.navigation.enums.DEEP_LINK
import com.swivel.navigation.router.Router
import timber.log.Timber
import javax.inject.Inject

/**
 * @author Yohan Dushmantha
 * @class ForgotPasswordOptionViewModel
 */
class ForgotPasswordOptionViewModel @Inject constructor(private val router : Router) : BaseViewModel() {


    /**---------------------------------------------------------------------------------------------*
     * INIT - START
     *----------------------------------------------------------------------------------------------*/

    override fun initViewArguments(deepLinkArguments: IBaseDeepLinkArguments?) {

    }

    /**---------------------------------------------------------------------------------------------*
     * INIT - END
     *----------------------------------------------------------------------------------------------*/

    /**---------------------------------------------------------------------------------------------*
     * EVENTS HANDLING - START
     *----------------------------------------------------------------------------------------------*/

    fun onTapSubmit(view : View?){
        view?.let {
            Timber.i("YC -> FPOS -> onTapSubmit")
            router.route(
                view.findNavController(),
                DEEP_LINK.SHARED_VERIFY_OTP,
                null,
                VerifyOTPDeepLinkArguments().apply
                {

                }
            )
        }
    }

    /**---------------------------------------------------------------------------------------------*
     * EVENTS HANDLING - END
     *----------------------------------------------------------------------------------------------*/

}