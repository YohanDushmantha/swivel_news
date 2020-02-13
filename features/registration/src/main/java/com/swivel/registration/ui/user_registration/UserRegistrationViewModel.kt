package com.swivel.registration.ui.user_registration

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.swivel.config.constants.AppStates
import com.swivel.core.ui.BaseViewModel
import com.swivel.models.base.DataSource
import com.swivel.models.entities.User
import com.swivel.models.entities.UserAuthentication
import com.swivel.models.features.IBaseDeepLinkArguments
import com.swivel.models.features.registration.user_registration.router_arguments.UserRegistrationDeepLinkArguments
import com.swivel.models.features.shared.info_box.enums.InfoBoxAppearance
import com.swivel.models.features.shared.info_box.router_arguments.InfoBoxDeepLinkArguments
import com.swivel.models.features.shared.info_box.router_arguments.InfoBoxViewConfig
import com.swivel.models.libs.navigation.enums.DEEP_LINK
import com.swivel.navigation.router.Router
import com.swivel.registration.R
import com.swivel.registration.ui.user_registration.enums.UserRegistrationInfoBoxID
import com.swivel.repository.news_service_repositories.UserAuthenticationRepository
import com.swivel.security.auth_manager.AuthManger
import com.swivel.security.auth_manager.enums.AuthManagerSharedKeys
import com.swivel.ui.base.exceptions.BaseViewModelException
import com.swivel.utility.soft_keyboard_manager.SoftKeyboardManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception
import java.util.*
import javax.inject.Inject

/**
 * @author Yohan Dushmantha
 * @class UserRegistrationViewModel
 */
class UserRegistrationViewModel @Inject constructor(
    private val router : Router,
    private val context : Context,
    private val softKeyboardManager: SoftKeyboardManager,
    private val userRegistrationFormValidator: UserRegistrationFormValidator,
    private val userAuthenticationRepository: UserAuthenticationRepository,
    private val appStates: AppStates,
    val userRegistrationFormData: UserRegistrationFormData

) : BaseViewModel() {

    private var receivedDeepLinkArgs : UserRegistrationDeepLinkArguments? = null

    /**---------------------------------------------------------------------------------------------*
     * INIT - START
     *----------------------------------------------------------------------------------------------*/

    override fun initViewArguments(deepLinkArguments: IBaseDeepLinkArguments?) {
        (deepLinkArguments as? UserRegistrationDeepLinkArguments)?.let {
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

    /**
     * executes when clicking already have an account button
     *
     * @param view
     */
    fun onTapAlreadyHaveAnAccount(view : View?){
        view?.let {
            redirectToLoginPage(it.findNavController())
        }
    }

    /**---------------------------------------------------------------------------------------------*
     * EVENTS HANDLING - END
     *----------------------------------------------------------------------------------------------*/

    /**---------------------------------------------------------------------------------------------*
     * VALIDATE FORM DATA WITH VALIDATION LIBRARY - START
     *----------------------------------------------------------------------------------------------*/

    /**
     * validate user registartion form data with validation library
     * if validation success, parse data to store inside local storage and redirect to login page
     * otherwise show error message
     *
     * @param navController Navigation Controller
     */
    fun validateFormDataFromMobileSide(navController: NavController?){
        try {
            //isLoading.postValue(true)
            userRegistrationFormValidator.validateFormData(
                userRegistrationFormData
            ).let {
                if(it.isValid){
                    saveUser(navController)
                }else{
                    //isLoading.postValue(false)
                    infoBoxHandler.showErrorInfoBox(
                        router,
                        navController,
                        it,
                        UserRegistrationInfoBoxID.FORM_DATA_VALIDATION_ERROR.infoBoxId,
                        null
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
     * HANDLE REGISTRATION PROCCESS - START
     *----------------------------------------------------------------------------------------------*/

    /**
     * request to save authentication user object into local storage
     *
     * @param navController navigation controller
     */
    private fun saveUser(navController: NavController?){
        try {
            //create user object
            val user = User(
                userRegistrationFormData.firstName,
                userRegistrationFormData.lastName,
                userRegistrationFormData.email,
                userRegistrationFormData.password
            )

            //save authentication obejct into local storage
            viewModelScope.launch {
                val userAuthentication = userAuthenticationRepository.registerUser(
                    UserAuthentication(
                        false,
                        null,
                        user
                    ),
                    AuthManagerSharedKeys.USER_AUTHENTICATION.key,
                    DataSource.LOCAL,
                    "User"
                )
                handleUserRegistrationResult(userAuthentication,navController)
            }
        }catch (ex : Exception){
            Timber.e(ex)
            //isLoading.postValue(false)
            showValidationErrorMessage(navController,ex.message)
        }

    }

    /**
     * handle user registration result
     * if user registered successfully need to show success message and redirect to login page
     * otherwise should show error message
     *
     * @param userAuthentication user authentication object to be saved
     * @param navController navigation contrller
     */
    private suspend fun handleUserRegistrationResult(userAuthentication: UserAuthentication?,navController: NavController?){
        try {
            userAuthentication?.let {
                navController?.let {
                    router.route(
                        navController,
                        DEEP_LINK.SHARED_INFO_BOX,
                        null,
                        InfoBoxDeepLinkArguments().apply {
                            infoBoxId = UserRegistrationInfoBoxID.FORM_DATA_REGISTRATION_SUCCESS.infoBoxId
                            detail = context.getString(R.string.user_registration_success_message)
                            viewConfig = InfoBoxViewConfig().apply {
                                infoBoxType = InfoBoxAppearance.SUCCESS
                                visibleTime = 3000
                            }
                        }
                    )
                    Timber.i("YD -> REDIRECT TO LOGIN PAGE DELAY START")
                    delay(4000)
                    redirectToLoginPage(navController)
                    Timber.i("YD -> REDIRECT TO LOGIN PAGE")
                } ?: throw BaseViewModelException("Nav Controller Not found when showing success info box")
                return
            }
            appStates.userAuth?.apply {
                this.loginStatus = false
                this.sessionID = null
                this.user = null
            }
            showValidationErrorMessage(navController,context.getString(R.string.user_registration_custom_error_message))
        }catch (ex : Exception){
            Timber.e(ex)
            //isLoading.postValue(false)
            showValidationErrorMessage(navController,ex.message)
        }finally {
            //isLoading.postValue(false)
        }
    }

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
            UserRegistrationInfoBoxID.FORM_DATA_VALIDATION_ERROR.infoBoxId
        )
    }

    /**
     * redirect to login page
     * @param navController navigation controller
     */
    private fun redirectToLoginPage(navController: NavController){
        router.popBackStack(navController,navController.currentDestination?.id!!,true)
        router.route(
            navController,
            DEEP_LINK.LOGIN,
            null
        )
    }


    /**---------------------------------------------------------------------------------------------*
     * HANDLE REGISTRATION PROCCESS - END
     *----------------------------------------------------------------------------------------------*/




}