package com.swivel.login.ui.verify_password

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.swivel.config.constants.AppStates
import com.swivel.core.ui.BaseViewModel
import com.swivel.login.R
import com.swivel.login.ui.verify_password.enums.VerifyPasswordInfoBoxId
import com.swivel.login.ui.verify_password.exceptions.VerifyPasswordAuthenticationException
import com.swivel.models.base.AppConfig
import com.swivel.models.base.AppConstants
import com.swivel.models.base.DataSource
import com.swivel.models.dto.driver_authentication_api.request.DriverAuthenticationRequest
import com.swivel.models.entities.UserAuthentication
import com.swivel.models.features.IBaseDeepLinkArguments
import com.swivel.models.features.home.home.router_arguments.HomeDeepLinkArguments
import com.swivel.models.features.login.verify_password.router_arguments.VerifyPasswordDeepLinkArguments
import com.swivel.models.libs.navigation.enums.DEEP_LINK
import com.swivel.navigation.router.Router
import com.swivel.repository.news_service_repositories.UserAuthenticationRepository
import com.swivel.security.auth_manager.AuthManger
import com.swivel.utility.soft_keyboard_manager.SoftKeyboardManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

/**
 * @author Yohan Dushmantha
 * @class VerifyPasswordViewModel
 */

class VerifyPasswordViewModel @Inject constructor(
    private val router : Router,
    private val context : Context,
    val verifyPasswordFormData: VerifyPasswordFormData,
    private val verifyPasswordFormValidator: VerifyPasswordFormValidator,
    private val userAuthenticationRepository : UserAuthenticationRepository,
    private val appStates: AppStates,
    private val appConfig: AppConfig,
    private val appConstants: AppConstants,
    private val softKeyboardManager: SoftKeyboardManager,
    private val authManager : AuthManger
) : BaseViewModel() {

    private var receivedDeepLinkArgs : VerifyPasswordDeepLinkArguments? = null

    /**---------------------------------------------------------------------------------------------*
     * INIT - START
     *----------------------------------------------------------------------------------------------*/

    override fun initViewArguments(deepLinkArguments: IBaseDeepLinkArguments?) {
        (deepLinkArguments as? VerifyPasswordDeepLinkArguments)?.let {
            receivedDeepLinkArgs = it
        }
    }

    /**---------------------------------------------------------------------------------------------*
     * INIT - END
     *----------------------------------------------------------------------------------------------*/

    /**---------------------------------------------------------------------------------------------*
     * EVENTS HANDLING - START
     *----------------------------------------------------------------------------------------------*/

    fun onTapSubmit(view : View?){
        view?.let {
            validateFormDataFromMobileSide(view.findNavController())
        }
    }

    /**
     * callback function for action done event
     * @param it TextView focused or selected edit text when clicked action done button
     */
    val passwordDone : (TextView) -> Unit = { textView ->
        softKeyboardManager.hideKeyboard(textView)
        validateFormDataFromMobileSide(textView.findNavController())
    }

    fun onTapForgotPassword(view : View?){
        view?.let {
            Timber.i("YC -> VP -> onTapForgotPassword")
            router.route(
                it.findNavController(),
                VerifyPasswordFragmentDirections.actionVerifyPasswordFragmentToForgotPasswordOptionFragment()
            )
        }
    }

    /**---------------------------------------------------------------------------------------------*
     * EVENTS HANDLING - END
     *----------------------------------------------------------------------------------------------*/

    /**---------------------------------------------------------------------------------------------*
     * VERIFY MOBILE NUMBER WITH VALIDATION LIBRARY - START
     *----------------------------------------------------------------------------------------------*/

    /**
     * validate form data from mobile side through validation library, if validation is success parse
     * form data for server side validation otherwise showing validation error
     */
     fun validateFormDataFromMobileSide(navController: NavController){
        verifyPasswordFormValidator.validateFormData(
            verifyPasswordFormData
        ).let {
            if(it.isValid){
                authenticate(verifyPasswordFormData,navController)
            }else{
                infoBoxHandler.showErrorInfoBox(
                    router,
                    navController,
                    it,
                    VerifyPasswordInfoBoxId.PASSWORD_VALIDATION_ERROR.infoBoxId
                )
            }
        }
    }

    /**---------------------------------------------------------------------------------------------*
     * VERIFY MOBILE NUMBER WITH VALIDATION LIBRARY - END
     *----------------------------------------------------------------------------------------------*/

    /**---------------------------------------------------------------------------------------------*
     * VERIFY MOBILE NUMBER WITH BACKEND SERVER - START
     *----------------------------------------------------------------------------------------------*/

    /**
     * authenticate driver credentials with backend if authentication success, insert session and
     * user details into shared preferences and redirect to home page otherwise show error
     *
     * @param verifyPasswordFormData object for getting form data of verify password
     * @param navController navigation controller
     */
    private fun authenticate(verifyPasswordFormData: VerifyPasswordFormData,navController: NavController){
//        viewModelScope.launch {
//            try {
//                isLoading.postValue(true)
//                withContext(Dispatchers.Main){
//                    userAuthenticationRepository.authenticateUser(
//                        getDriverAuthenticationRequest(),
//                        DataSource.REMOTE,
//                        context.getString(R.string.verify_password_authentication_error)
//                    )?.let { response ->
//                        response.result?.let {
//                            handleAuthenticationSuccess(it,navController)
//                        } ?: VerifyPasswordAuthenticationException("Driver Authentication not Found.")
//                    } ?: VerifyPasswordAuthenticationException("Driver Authentication Response not Found.")
//                }
//            }catch (ex : Exception){
//                Timber.e(ex)
//            }finally {
//                isLoading.postValue(false)
//            }
//        }
    }

    /**
     * get driver authentication request
     * @return DriverAuthenticationRequest if all the required attributes consists of data return
     * DriverAuthenticationRequest otherwise throws exception
     */
    private fun getDriverAuthenticationRequest() : DriverAuthenticationRequest{
        return DriverAuthenticationRequest().apply {

            receivedDeepLinkArgs?.userMobileNumber?.let {
                mobileNumber = it
            } ?: throw VerifyPasswordAuthenticationException("Mobile Number Not Found")

            password = verifyPasswordFormData.password

            deviceTypeID = appConstants.platformId

            appStates.deviceInfo?.let {
                deviceSerialNumber = it.imei
                deviceModel = it.model
                osVersion = it.os
            } ?: VerifyPasswordAuthenticationException("Device Info Not Loaded")

            appConfig.versionCode?.let {
                currentAppVersion = it
            } ?: VerifyPasswordAuthenticationException("Current App Version not loaded.")
        }
    }

    /**---------------------------------------------------------------------------------------------*
     * VERIFY MOBILE NUMBER WITH BACKEND SERVER - END
     *----------------------------------------------------------------------------------------------*/

    /**---------------------------------------------------------------------------------------------*
     * HANDLE AUTHENTICATION SUCCESS PROCESS - START
     *----------------------------------------------------------------------------------------------*/

    private suspend fun handleAuthenticationSuccess(
        userAuthentication: UserAuthentication,
        navController: NavController
    ){
        try {
            storeAuthenticationDetails(userAuthentication)?.let {
                if(it){
                    redirectToHomePage(navController)
                } else throw VerifyPasswordAuthenticationException()
            } ?: throw VerifyPasswordAuthenticationException()
        }catch (ex : Exception){
            Timber.e(ex)
        }
    }

    private fun prepareDriverAuthenticationObject(
        userAuthentication: UserAuthentication
    ) : UserAuthentication{
        return userAuthentication.apply {
            //mobileNumber = receivedDeepLinkArgs?.userMobileNumber
        }
    }

    private suspend fun storeAuthenticationDetails(
        userAuthentication: UserAuthentication
    ) : Boolean?{
        return authManager.storeUserAuthentication(prepareDriverAuthenticationObject(userAuthentication))
    }

    private suspend fun redirectToHomePage(navController: NavController){
        router.route(
            navController,
            DEEP_LINK.HOME_MAIN,
            null,
            HomeDeepLinkArguments().apply {

            }
        )
    }

    /**---------------------------------------------------------------------------------------------*
     * HANDLE AUTHENTICATION SUCCESS PROCESS - END
     *----------------------------------------------------------------------------------------------*/
}