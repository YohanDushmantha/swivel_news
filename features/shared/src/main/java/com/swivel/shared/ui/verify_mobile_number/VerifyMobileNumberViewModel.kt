package com.swivel.shared.ui.verify_mobile_number

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.swivel.core.ui.BaseViewModel
import com.swivel.models.base.DataSource
import com.swivel.models.dto.driver_authentication_api.request.MobileNumberVerificationRequest
import com.swivel.models.features.IBaseDeepLinkArguments
import com.swivel.models.features.login.verify_password.router_arguments.VerifyPasswordDeepLinkArguments
import com.swivel.models.features.shared.verify_mobile_number.router_arguments.VerifyMobileNumberDeepLinkArguments
import com.swivel.models.features.shared.verify_mobile_number.router_arguments.VerifyMobileNumberDestinationConfig
import com.swivel.models.features.shared.verify_otp.router_arguments.VerifyOTPDeepLinkArguments
import com.swivel.models.libs.navigation.enums.DEEP_LINK
import com.swivel.navigation.router.Router
import com.swivel.repository.news_service_repositories.UserAuthenticationRepository
import com.swivel.shared.R
import com.swivel.shared.ui.verify_mobile_number.enums.VerifyMobileNumberInfoBoxID
import com.swivel.shared.ui.verify_mobile_number.exceptions.VerifyMobileNumberDestinationDeepLinkNotFoundException
import com.swivel.utility.soft_keyboard_manager.SoftKeyboardManager
import com.swivel.models.libs.navigation.ValidationResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

/**
 * @author Yohan Dushmantha
 * @class VerifyMobileNumberViewModel
 */

class VerifyMobileNumberViewModel @Inject constructor(
    private val router : Router,
    private val context: Context,
    val verifyMobileNumberFormData: VerifyMobileNumberFormData,
    private val verifyMobileNumberFormValidator: VerifyMobileNumberFormValidator,
    private val softKeyboardManager: SoftKeyboardManager,
    private val userAuthenticationRepository: UserAuthenticationRepository
) : BaseViewModel(){
    var verifyMobileNumberDestinationConfig : MutableLiveData<VerifyMobileNumberDestinationConfig> = MutableLiveData()


    /**---------------------------------------------------------------------------------------------*
     * INIT - START
     *----------------------------------------------------------------------------------------------*/

    override fun initViewArguments(deepLinkArguments: IBaseDeepLinkArguments?) {
        (deepLinkArguments as? VerifyMobileNumberDeepLinkArguments).let {
            verifyMobileNumberDestinationConfig.postValue(it?.verifyMobileNumberDestinationConfig)
        }
    }

    /**---------------------------------------------------------------------------------------------*
     * INIT - END
     *----------------------------------------------------------------------------------------------*/

    /**---------------------------------------------------------------------------------------------*
     * EVENT HANDLING - START
     *----------------------------------------------------------------------------------------------*/

    /**
     * callback function for action done event
     * @param it TextView focused or selected edit text when clicked action done button
     */
    val signIn : (TextView) -> Unit = { textView ->
        Timber.i("YD -> Data Binding ${textView.editableText.toString()}")
        softKeyboardManager.hideKeyboard(textView)
        validateFromMobileSide(textView.editableText?.toString(),textView.findNavController())
    }

    /**
     * executing when tapping submit button
     * @param view view object
     */
    fun onTapSubmit(view : View?){
        view?.let {
            softKeyboardManager.hideKeyboard(view)
            validateFromMobileSide(verifyMobileNumberFormData.mobileNumber,it.findNavController())
        }
    }




    /**---------------------------------------------------------------------------------------------*
     * EVENT HANDLING - END
     *----------------------------------------------------------------------------------------------*/

    /**---------------------------------------------------------------------------------------------*
     * VERIFY MOBILE NUMBER WITH VALIDATION LIBRARY - START
     *----------------------------------------------------------------------------------------------*/

    /**
     * validate mobile number using validation library
     * if provided text is not a valid mobile number according to validation rules should show error
     * message otherwise validate mobile number with back end
     */
    private fun validateFromMobileSide(text : String?, navController: NavController){
        verifyMobileNumberFormValidator.validateFormData(verifyMobileNumberFormData).let { validationResult ->
            if(validationResult?.isValid!!){
                Timber.i("YD -> Data Binding Valid Mobile Number")
                verifyMobileNumberWithBackend("+94$text",navController)
            }else{
                infoBoxHandler.showErrorInfoBox(
                    router,
                    navController,
                    validationResult,
                    VerifyMobileNumberInfoBoxID.MOBILE_NUMBER_VALIDATION_ERROR.infoBoxID
                )
            }
        }
    }

    /**
     * show error message which is related to provided validation result
     * @param navController navigation controller
     * @param validationResult validation result object which is provided by validator
     */
    /*fun showValidationErrorMessage(navController: NavController, validationResult : ValidationResult){
        router.route(
            navController,
            DEEP_LINK.SHARED_INFO_BOX,
            null,
            InfoBoxDeepLinkArguments().apply {
                infoBoxId = VerifyMobileNumberInfoBoxID.MOBILE_NUMBER_VALIDATION_ERROR.infoBoxID
                detail = validationResult.message
                viewConfig = InfoBoxViewConfig().apply {
                    infoBoxType = InfoBoxAppearance.ERROR
                }
            }
        )
    }*/

    /**---------------------------------------------------------------------------------------------*
     * VERIFY MOBILE NUMBER WITH VALIDATION LIBRARY - END
     *----------------------------------------------------------------------------------------------*/

    /**---------------------------------------------------------------------------------------------*
     * VERIFY MOBILE NUMBER WITH BACKEND - START
     *----------------------------------------------------------------------------------------------*/

    /**
     * verify mobile number with backend, check whether provided mobile number already exists in the
     * system or new one
     * @param mobileNumber provided mobile number
     * @param navController
     */
    private fun verifyMobileNumberWithBackend(mobileNumber : String, navController: NavController){
        viewModelScope.launch {
            try {
                isLoading.postValue(true)
//                val driverVerificationResponse = withContext(Dispatchers.Main){
//                    userAuthenticationRepository.verifyDriverMobileNumber(
//                        MobileNumberVerificationRequest(
//                            mobileNumber
//                        ),
//                        DataSource.REMOTE,
//                        context.getString(R.string.mobile_number_verification_error)
//                    )
//                }
//                driverVerificationResponse?.result?.let {
//                    userValidationResult(mobileNumber,it.isUserExists!!,navController)
//                } ?: infoBoxHandler.showErrorInfoBox(
//                    router,
//                    navController,
//                    ValidationResult(
//                        false,
//                        context.getString(R.string.mobile_number_verification_error)
//                    ),
//                    VerifyMobileNumberInfoBoxID.MOBILE_NUMBER_VALIDATION_ERROR.infoBoxID
//                )
            }catch (ex : Exception){
                Timber.e(ex)
            }finally {
                isLoading.postValue(false)
            }
        }

        //userValidationResult(mobileNumber,true,navController)
    }

    /**
     * according to the result that is received from server, need to redirect to next page
     */
    private fun userValidationResult(mobileNumber: String ,isExistingUser : Boolean, navController: NavController){
        var deepLinkArguments : IBaseDeepLinkArguments? = null
        var deepLink : DEEP_LINK? = null
        if(isExistingUser){
            // set deep link of password verification page
            deepLink = verifyMobileNumberDestinationConfig.value?.alreadyExistsUserDestination
            when(verifyMobileNumberDestinationConfig.value?.alreadyExistsUserDestination){
                DEEP_LINK.VERIFY_PASSWORD -> {
                    deepLinkArguments = VerifyPasswordDeepLinkArguments(mobileNumber)
                }
            }
        }else{
            // set deep link of otp verification page
            deepLink = verifyMobileNumberDestinationConfig.value?.newUserDestination
            when(verifyMobileNumberDestinationConfig.value?.newUserDestination){
                DEEP_LINK.SHARED_VERIFY_OTP -> {
                    deepLinkArguments = VerifyOTPDeepLinkArguments(mobileNumber)
                }
            }
        }
        deepLink?.let {dl ->
            deepLinkArguments?.let {
                router.route(navController,dl,null,it)
            }
        } ?: throw VerifyMobileNumberDestinationDeepLinkNotFoundException()

    }

    /**---------------------------------------------------------------------------------------------*
     * VERIFY MOBILE NUMBER WITH BACKEND - END
     *----------------------------------------------------------------------------------------------*/
}