package com.swivel.onboarding.ui.walkthrough

import android.content.Context
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import com.swivel.core.ui.BaseViewModel
import com.swivel.models.features.IBaseDeepLinkArguments
import com.swivel.models.features.login.login.router_arguments.LoginDeepLinkArguments
import com.swivel.models.features.registration.user_registration.router_arguments.UserRegistrationDeepLinkArguments
import com.swivel.models.features.sample_feature.post.router_arguments.PostDeepLinkArguments
import com.swivel.models.features.shared.confirm_box.enums.ConfirmBoxAppearance
import com.swivel.models.features.shared.confirm_box.enums.ConfirmBoxButtonAppearance
import com.swivel.models.features.shared.confirm_box.event_listeners.ConfirmBoxEventResult
import com.swivel.models.features.shared.confirm_box.router_arguments.ConfirmBoxButtonConfig
import com.swivel.models.features.shared.confirm_box.router_arguments.ConfirmBoxDeepLinkArguments
import com.swivel.models.features.shared.confirm_box.router_arguments.ConfirmBoxViewConfig
import com.swivel.models.features.shared.verify_mobile_number.router_arguments.VerifyMobileNumberDeepLinkArguments
import com.swivel.models.features.shared.verify_mobile_number.router_arguments.VerifyMobileNumberDestinationConfig
import com.swivel.models.libs.navigation.enums.DEEP_LINK
import com.swivel.navigation.router.Router
import com.swivel.onboarding.R
import com.swivel.onboarding.ui.walkthrough.enums.WalkthroughConfirmBoxId
import javax.inject.Inject

/**
 * @author Yohan Dushmantha
 * @class WalkthroughViewModel
 */

class WalkthroughViewModel @Inject constructor(
    private val router: Router,
    private val context: Context
) : BaseViewModel() {

    val shouldCloseApp = MutableLiveData<Boolean>()

    val walkthroughPages = listOf<WalkthroughViewPagerModel>(
        WalkthroughViewPagerModel(R.string.walkthrough_page1_title, R.drawable.sample_walkthrough_slide_1),
        WalkthroughViewPagerModel(R.string.walkthrough_page2_title, R.drawable.sample_walkthrough_slide_2),
        WalkthroughViewPagerModel(R.string.walkthrough_page3_title, R.drawable.sample_walkthrough_slide_3),
        WalkthroughViewPagerModel(R.string.walkthrough_page4_title, R.drawable.sample_walkthrough_slide_4),
        WalkthroughViewPagerModel(R.string.walkthrough_page5_title, R.drawable.sample_walkthrough_slide_5)
    )

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

    /**
     * trigger when tapping on login button
     */
    fun onTapLogin(view : View?){
        view?.let {
            router.route(
                it.findNavController(),
                DEEP_LINK.LOGIN,
                null,
                LoginDeepLinkArguments()
            )
        }
    }

    /**
     * trigger when tapping on registration button
     */
    fun onTapRegistration(view : View?){
        view?.let {
            router.route(
                it.findNavController(),
                DEEP_LINK.USER_REGISTRATION,
                null,
                UserRegistrationDeepLinkArguments()
            )
        }
    }

    /**----------------------------------------------------------------------------------------------------------------*
     * EVENTS HANDLING - END
     *-----------------------------------------------------------------------------------------------------------------*/


    /**----------------------------------------------------------------------------------------------------------------*
     * CONFIRMATION MESSAGE HANDLING - END
     *-----------------------------------------------------------------------------------------------------------------*/

    /**
     * show exit confirmation message to user, user can choose whether need to exit from the application
     * or not
     * @param navController navigation controller
     */
    fun showExitConfirmation(navController: NavController){
        router.route(navController,
            DEEP_LINK.SHARED_CONFIRM_BOX,null,
            ConfirmBoxDeepLinkArguments().apply {
                confirmBoxId = WalkthroughConfirmBoxId.EXIT_APP.confirmBoxId
                detail = context.getString(R.string.walkthrough_exit_confirmation)
                viewConfig = ConfirmBoxViewConfig().apply {
                    isCancellable = false
                    confirmBoxAppearance = ConfirmBoxAppearance.DEFAULT

                    val buttonConfigList : HashMap<Int, ConfirmBoxButtonConfig> = HashMap()

                    buttonConfigList.put(
                        ConfirmBoxButtonAppearance.POSITIVE.appearanceID,
                        ConfirmBoxButtonConfig(
                            callbackFunction = confirmationBoxResult
                        )
                    )

                    buttonConfigList.put(
                        ConfirmBoxButtonAppearance.NEGATIVE.appearanceID,
                        ConfirmBoxButtonConfig(
                            callbackFunction = confirmationBoxResult
                        )
                    )

                    buttonList = buttonConfigList

                }
            })
    }

    /**
     * handle confirmation box result, every confirmation box should use this method as callback
     */
    private val confirmationBoxResult : (
        confirmBoxID : Short,
        confirmBoxButtonAppearance : ConfirmBoxButtonAppearance,
        result : ConfirmBoxEventResult?
    ) -> Unit = { confirmBoxID, confirmBoxButtonAppearance, result ->
        when(confirmBoxID){
            WalkthroughConfirmBoxId.EXIT_APP.confirmBoxId -> {
                handleExitConfirmationResult(confirmBoxButtonAppearance,result)
            }
        }
    }

    /**
     * handle exit confirmation result
     * if result is true should close app otherwise keep it as it current state
     */
    fun handleExitConfirmationResult(
        appearance: ConfirmBoxButtonAppearance,
        result: ConfirmBoxEventResult?
    ){
        when(appearance){
            ConfirmBoxButtonAppearance.POSITIVE -> {
                shouldCloseApp.postValue(true)
            }
            ConfirmBoxButtonAppearance.NEGATIVE -> {

            }
        }
    }

    /**----------------------------------------------------------------------------------------------------------------*
     * CONFIRMATION MESSAGE HANDLING - END
     *-----------------------------------------------------------------------------------------------------------------*/

}