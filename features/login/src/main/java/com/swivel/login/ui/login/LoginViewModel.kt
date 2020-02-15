package com.swivel.login.ui.login

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.swivel.config.constants.AppStates
import com.swivel.core.ui.BaseViewModel
import com.swivel.login.R
import com.swivel.login.ui.login.enums.LoginInfoBoxId
import com.swivel.models.entities.User
import com.swivel.models.features.IBaseDeepLinkArguments
import com.swivel.models.features.login.login.router_arguments.LoginDeepLinkArguments
import com.swivel.models.libs.navigation.enums.DEEP_LINK
import com.swivel.navigation.router.Router
import com.swivel.security.auth_manager.AuthManger
import com.swivel.utility.soft_keyboard_manager.SoftKeyboardManager
import timber.log.Timber
import java.lang.Exception
import java.util.*
import javax.inject.Inject

/**
 * @author Yohan Dushmantha
 * @class LoginViewModel
 */
class LoginViewModel @Inject constructor(
    private val router : Router,
    private val context : Context,
    private val softKeyboardManager: SoftKeyboardManager,
    private val loginFormValidator: LoginFormValidator,
    private val appStates: AppStates,
    private val authManger : AuthManger,
    val loginFormData: LoginFormData
) : BaseViewModel(){

    private var receivedDeepLinkArgs : LoginDeepLinkArguments? = null

    /**---------------------------------------------------------------------------------------------*
     * INIT - START
     *----------------------------------------------------------------------------------------------*/

    override fun initViewArguments(deepLinkArguments: IBaseDeepLinkArguments?) {
        (deepLinkArguments as? LoginDeepLinkArguments)?.let {
            receivedDeepLinkArgs = it
        }
    }

    /**---------------------------------------------------------------------------------------------*
     * INIT - END
     *----------------------------------------------------------------------------------------------*/

    /**---------------------------------------------------------------------------------------------*
     * EVENTS HANDLING - START
     *----------------------------------------------------------------------------------------------*/

    /**
     * executes when clicking on submit button
     *
     * @param view
     */
    fun onTapSubmit(view : View?){
        view?.let {
            softKeyboardManager.hideKeyboard(view)
            validateFormData(view.findNavController())
        }
    }

    /**
     * callback function for action done event
     * @param it TextView focused or selected edit text when clicked action done button
     */
    val passwordDone : (TextView) -> Unit = { textView ->
        softKeyboardManager.hideKeyboard(textView)
        validateFormData(textView.findNavController())
    }

    /**
     * executes when clicking already have an account button
     *
     * @param view
     */
    fun onTapCreateNewAccount(view : View?){
        view?.let {
            redirectToRegistrationPage(it.findNavController())
        }
    }

    /**---------------------------------------------------------------------------------------------*
     * EVENTS HANDLING - END
     *----------------------------------------------------------------------------------------------*/

    /**---------------------------------------------------------------------------------------------*
     * VALIDATE FORM DATA WITH VALIDATION LIBRARY - START
     *----------------------------------------------------------------------------------------------*/

    /**
     * validate user login form data with validation library
     * if validation success, authenticate user credentials with local storage
     * otherwise show error message
     *
     * @param navController Navigation Controller
     */
    fun validateFormData(navController: NavController?){
        try {
            //isLoading.postValue(true)
            loginFormValidator.validateFormData(
                loginFormData
            ).let {
                if(it.isValid){
                    authenticateUser(navController)
                }else{
                    //isLoading.postValue(false)
                    infoBoxHandler.showErrorInfoBox(
                        router,
                        navController,
                        it,
                        LoginInfoBoxId.FORM_DATA_VALIDATION_ERROR.infoBoxId
                    )
                }
            }
        }catch (ex : Exception){
            Timber.e(ex)
            //isLoading.postValue(false)
            showValidationErrorMessage(navController,ex.message)
        }
    }



    /**---------------------------------------------------------------------------------------------*
     * VALIDATE FORM DATA WITH VALIDATION LIBRARY - END
     *----------------------------------------------------------------------------------------------*/

    /**---------------------------------------------------------------------------------------------*
     * AUTHENTICATION - START
     *----------------------------------------------------------------------------------------------*/

    /**
     * authenticate user provided credentials with stored data
     * if authentication get successful redirect to home page otherwise show error
     *
     * @param navController navigation contrller
     */
    private fun authenticateUser(navController: NavController?){
        try {
            navController?.let {
                appStates.userAuth?.user?.let {user: User ->
                    if(loginFormData.email.equals(user.email) && loginFormData.password.equals(user.password)){
                        authManger.storeUserAuthentication(appStates.userAuth?.apply {
                            sessionID = UUID.randomUUID().toString()
                            loginStatus = true
                        })
                        appStates.userAuth = authManger.fetchUserAuthentication()
                        redirectToHomePage(navController)
                    } else{
                        showValidationErrorMessage(navController,context.getString(R.string.login_custom_error_message))
                    }
                }  ?: showValidationErrorMessage(navController,context.getString(R.string.login_custom_error_message))

            } ?: showValidationErrorMessage(navController,context.getString(R.string.login_custom_error_message))
        }catch (ex : Exception){
            Timber.i(ex)
            showValidationErrorMessage(navController,context.getString(R.string.login_custom_error_message))
        }finally {
            //isLoading.postValue(false)
        }

    }

    /**---------------------------------------------------------------------------------------------*
     * AUTHENTICATION - END
     *----------------------------------------------------------------------------------------------*/

    /**
     * show validation error message by parsing string
     *
     * @param navController navigation controller
     * @param message error message
     */
    private fun showValidationErrorMessage(navController: NavController?,message : String?){
        infoBoxHandler.showErrorInfoBox(
            router,
            navController,
            message,
            LoginInfoBoxId.FORM_DATA_VALIDATION_ERROR.infoBoxId
        )
    }

    /**
     * redirect to registration page
     * @param navController navigation controller
     */
    private fun redirectToRegistrationPage(navController: NavController){
        router.popBackStack(navController,navController.currentDestination?.id!!,true)
        router.route(
            navController,
            DEEP_LINK.USER_REGISTRATION,
            null
        )
    }

    /**
     * redirect to home page
     * @param navController navigation controller
     */
    private fun redirectToHomePage(navController: NavController){
        router.popBackStack(navController,navController.currentDestination?.id!!,true)
        router.route(
            navController,
            DEEP_LINK.HOME_MAIN,
            null
        )
    }

}