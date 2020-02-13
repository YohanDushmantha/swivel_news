package com.swivel.onboarding.ui.splash

import android.content.Context
import android.content.pm.PackageManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.swivel.config.constants.AppStates
import com.swivel.core.ui.BaseViewModel
import com.swivel.crypto.keystore_manager.KeystoreManager
import com.swivel.models.features.IBaseDeepLinkArguments
import com.swivel.navigation.router.Router
import com.swivel.models.base.AppConstants
import com.swivel.models.dto.common_api.response.SystemConfigurationResponse
import com.swivel.onboarding.R
import com.swivel.onboarding.ui.splash.enums.SplashInfoBoxID
import com.swivel.repository.news_service_repositories.SystemConfigurationRepository
import com.swivel.security.root.RootChecker
import com.swivel.security.auth_manager.AuthManger
import com.swivel.utility.device_info_manger.IDeviceInfoManager
import com.swivel.utility.permission_manager.IPermissionManager
import kotlinx.coroutines.*
import timber.log.Timber
import javax.inject.Inject
import java.lang.Exception

/**
 * @author Yohan Dushmantha
 * @class SplashViewModel
 */

class SplashViewModel @Inject constructor(
    private val context: Context?,
    private val router: Router,
    private val authManger: AuthManger,
    private val rootChecker: RootChecker,
    private val keystoreManager: KeystoreManager,
    private val deviceInfoManager: IDeviceInfoManager,
    private val permissionManager: IPermissionManager,
    private val systemConfigurationRepository: SystemConfigurationRepository,
    private val appStates: AppStates,
    private val appConstants: AppConstants
) : BaseViewModel() {

    var name = "Spalash Screen from view Model"
    var isRequestToGrantPhoneStatePermission = MutableLiveData<Boolean>()
    var isRequestToGrantAccessNetworkPermission = MutableLiveData<Boolean>()
    var isRedirectToHomeUI = MutableLiveData<Boolean>()
    var isRedirectTpWalkthroughUI = MutableLiveData<Boolean>()
    var systemConfigurationResponseLiveData = MutableLiveData<SystemConfigurationResponse>()
    val taskProgressText = MutableLiveData<String>()
    val shouldCloseApp = MutableLiveData<Boolean>()

    val phoneStatePermissionRequestCode = 1
    val accessNetworkStatePermissionRequestCode = 2

    private var navController : NavController? = null
    var systemConfigurationAttempts = 0

    /**---------------------------------------------------------------------------------------------*
     * INIT - START
     *----------------------------------------------------------------------------------------------*/

    override fun initViewArguments(deepLinkArguments: IBaseDeepLinkArguments?) {

    }

    /**
     * initialize app
     * initialize utilities
     * validate session
     * validate root status of the device
     * gather device info
     * @param navController Navigation controller
     */
    fun initializeApp(navController: NavController?){
        this.navController = navController

        initializeDataLoading()
    }


    /**---------------------------------------------------------------------------------------------*
     * INIT - END
     *----------------------------------------------------------------------------------------------*/

    /**---------------------------------------------------------------------------------------------*
     * TASK PROGRESS - START
     *----------------------------------------------------------------------------------------------*/

    /**
     * start to checking device status and load necessary data for starting app
     */
    private fun initializeDataLoading(){
        viewModelScope.launch (Dispatchers.Main){
            //create keys inside vault
            createKeysInVault(context, executeNextStep)

        }
    }

    /**
     * This will only create a certificate once as it checks internally whether a certificate with
     * the given name already exists.
     * if keys are created or already exist should invoke callback function with SPLASH_TASKS.CRAETE_KEYSTORE_SUCCESS
     * otherwise SPLASH_TASKS.CREATE_KEYSTORE_FAILURE
     *
     * @param context Context Application Context
     * @param callback should invoke this function according to received result
     */
    private suspend fun createKeysInVault(
        context: Context?,
        callback : (context : Context? ,task : SplashTasks) -> Unit)
    {
        withContext(Dispatchers.Main){
            setTaskProgress(context?.getString(R.string.task_loading_msg_keystore_generate)!!)
            try {
                keystoreManager.createMasterKey()
                callback(context,SplashTasks.CREATE_KEYSTORE_SUCCESS)
            } catch (e: Exception) {
                callback(context, SplashTasks.CREATE_KEYSTORE_FAILURE)
            }
        }
    }

    /**
     * check whether device is rooted or not
     * if device is rooted should invoke callback function with SPLASH_TASKS.DEVICE_IS_ROOTED
     * otherwise SPLASH_TASKS.DEVICE_IS_NOT_ROOTED
     *
     * @param context Context Application Context
     * @param callback should invoke this function according to received result
     */
    private suspend fun checkIsRooted(
        context : Context? ,
        callback : (context : Context? ,task : SplashTasks) -> Unit)
    {
        withContext(Dispatchers.Main){
            setTaskProgress(context?.getString(R.string.task_loading_msg_root_checking)!!)
            if (rootChecker.isRooted(context)){
                callback(context, SplashTasks.DEVICE_IS_ROOTED)
            }else{
                callback(context, SplashTasks.DEVICE_IS_NOT_ROOTED)
            }

        }
    }

    /**
     * check whether phone state permission is granted or not
     * if permission is granted should invoke callback function with SPLASH_TASKS.HAS_PHONE_STATE_PERMISSION
     * otherwise SPLASH_TASKS.NO_PHONE_STATE_PERMISSION
     *
     * @param context Context Application Context
     * @param callback should invoke this function according to received result
     */
    private suspend fun checkPhoneStatePermission(
        context: Context?,
        callback : (context : Context? ,task : SplashTasks) -> Unit
    ){
        withContext(Dispatchers.Main){
            setTaskProgress(context?.getString(R.string.task_loading_msg_check_phone_state_permission)!!)
            if (permissionManager.isPermissionGranted(context, android.Manifest.permission.READ_PHONE_STATE)){
                callback(context,SplashTasks.HAS_PHONE_STATE_PERMISSION)
            }else{
                callback(context, SplashTasks.NO_PHONE_STATE_PERMISSION)
            }
        }
    }

    /**
     * check whether network state permission is granted or not
     * if permission is granted should invoke callback function with SPLASH_TASKS.HAS_PHONE_STATE_PERMISSION
     * otherwise SPLASH_TASKS.NO_PHONE_STATE_PERMISSION
     *
     * @param context Context Application Context
     * @param callback should invoke this function according to received result
     */
    private suspend fun checkAccessNetworkStatePermission(
        context: Context?,
        callback: (context: Context?, task: SplashTasks) -> Unit
    ){
        withContext(Dispatchers.Main){
            setTaskProgress(context?.getString(R.string.task_loading_msg_check_access_network_state_permission)!!)
            if(permissionManager.isPermissionGranted(context,android.Manifest.permission.ACCESS_NETWORK_STATE)){
                callback(context,SplashTasks.HAS_ACCESS_NETWORK_STATE_PERMISSION)
            }else{
                callback(context,SplashTasks.NO_ACCESS_NETWORK_STATE_PERMISSION)
            }
        }
    }

    /**
     * try to load device info
     * if device info successfully loaded should invoke callback function with SPLASH_TASKS.DEVICE_INFO_LOADED
     * otherwise SPLASH_TASKS.DEVICE_INFO_NOT_LOADED
     *
     * if system can identified and gathered device info should be loaded into AppStates otherwise
     * clear the device info inside App States
     *
     * @param context Context Application Context
     * @param callback should invoke this function according to received result
     */
    private suspend fun loadDeviceInfo(
        context: Context?,
        callback : (context : Context? ,task : SplashTasks) -> Unit
    ){
        withContext(Dispatchers.Main){
            try {
                setTaskProgress(context?.getString(R.string.task_loading_msg_loading_device_info)!!)
                appStates.deviceInfo = deviceInfoManager.info(context)
                callback(context,SplashTasks.DEVICE_INFO_LOADED)
            }catch (exeption : java.lang.Exception){
                appStates.deviceInfo = null
                callback(context,SplashTasks.DEVICE_INFO_NOT_LOADED)
            }}
    }

    /**
     * check whether session id has stored inside shared preferences
     * if there is a session id inside shared pref should invoke callback function with SPLASH_TASKS.HAS_VALID_SESSION
     * otherwise SPLASH_TASKS.NO_VALID_SESSION
     *
     * if there is valid session session info should be loaded into AppStates otherwise should be
     * cleared
     *
     * @param context Context Application Context
     * @param callback should invoke this function according to received result
     */
    private suspend fun checkSession (
        context: Context?,
        callback : (context : Context? ,task : SplashTasks) -> Unit
    ){
        withContext(Dispatchers.Main){
            setTaskProgress(context?.getString(R.string.task_loading_msg_validating_session)!!)
            if (authManger.isActiveSession()){
                callback(context, SplashTasks.HAS_VALID_SESSION)
            }else{
                callback(context,SplashTasks.NO_VALID_SESSION)
            }
        }
    }

    /**
     * try to load user auth data from shared preferences
     * if user auth data can be loaded from shared preferences should invoke callback with SPLASH_TASKS.USER_DETAILS_LOADED
     * otherwise SPLASH_TASKS.USER_DETAILS_NOT_LOADED
     *
     * if user auth data can be loaded, should be stored into AppStates otherwise should be cleared
     *
     * @param context Context Application Context
     * @param callback should invoke this function according to received result
     */
    private suspend fun loadAuthData(
        context: Context?,
        callback : (context : Context? ,task : SplashTasks) -> Unit
    ){
        withContext(Dispatchers.Main){
            setTaskProgress(context?.getString(R.string.task_loading_msg_loading_session_data)!!)
            authManger.fetchUserAuthentication()?.let {
                appStates.userAuth = it
                callback(context,SplashTasks.AUTH_DATA_LOADED)
            } ?: callback(context,SplashTasks.AUTH_DATA_NOT_LOADED)
        }
    }

    /**
     * try to load system configuration from remote api
     * if user can be loaded system configuration from remote api should invoke callback with
     * SPLASH_TASKS.SYSTEM_CONFIGURATION_LOADED_SUCCESSFULLY otherwise
     * SPLASH_TASKS.SYSTEM_CONFIGURATION_LOADING_FAILED
     *
     * if system configuration can be loaded, should be stored into AppStates otherwise should be cleared
     *
     * @param context Context Application Context
     * @param callback should invoke this function according to received result
     */
    private suspend fun requestToLoadSystemConfiguration(
        context: Context?,
        callback : (context : Context? ,task : SplashTasks) -> Unit
    ){
        callback(context,SplashTasks.SYSTEM_CONFIGURATION_LOADED_SUCCESSFULLY)
        //TODO need to implement system configuration loading logic from server
    }

    /**
     * function for parse as a callback function for task progress methods
     *
     * @param context Context Application Context
     * @param task SPLASH_TASK progress task event
     */
    private val executeNextStep : (context : Context? ,task: SplashTasks) -> Unit = {
            context, task -> execute(context,task)
    }

    /**
     * check progress event id that is received from executeNextStep functions and do needful things
     * according to received events
     *
     * @param context Context Application Context
     * @param task SPLASH_TASK progress task event
     *
     */
    private fun execute(context: Context?, task: SplashTasks){
        viewModelScope.launch(Dispatchers.Main){
            Timber.i("YC -> TASK CALLBACKES -> ${task.text}")
            when(task){
                /** KEYSTORE - START */

                SplashTasks.CREATE_KEYSTORE_SUCCESS -> {
                    checkIsRooted(context, executeNextStep)
                }
                SplashTasks.CREATE_KEYSTORE_FAILURE -> {

                    infoBoxHandler.showErrorInfoBox(
                        router,
                        navController,
                        context?.getString(R.string.task_loading_error_keystore_issue),
                        SplashInfoBoxID.TASK_PROGRESS_ERROR_KEYSTORE_GENERATION_FAILURE.infoBoxID
                    )
                }

                /** CREATE KEYSTORE - END */

                /** DEVICE ROOT STATUS - START */

                SplashTasks.DEVICE_IS_ROOTED -> {
                    infoBoxHandler.showErrorInfoBox(
                        router,
                        navController,
                        context?.getString(R.string.task_loading_error_device_is_rooted),
                        SplashInfoBoxID.TASK_PROGRESS_ERROR_DEVICE_IS_ROOTED.infoBoxID
                    )
                }
                SplashTasks.DEVICE_IS_NOT_ROOTED ->{
                    checkPhoneStatePermission(context,executeNextStep)
                }

                /** DEVICE ROOT STATUS - END */

                /** PHONE STATE PERMISSION - START */

                SplashTasks.HAS_PHONE_STATE_PERMISSION -> {
                    checkAccessNetworkStatePermission(context,executeNextStep)
                }
                SplashTasks.NO_PHONE_STATE_PERMISSION -> {
                    isRequestToGrantPhoneStatePermission.postValue(true)
                }
                SplashTasks.GRANT_PHONE_STATE_PERMISSION -> {
                    checkAccessNetworkStatePermission(context,executeNextStep)
                }
                SplashTasks.DENY_PHONE_STATE_PERMISSION -> {
                    infoBoxHandler.showErrorInfoBox(
                        router,
                        navController,
                        context?.getString(R.string.task_loading_error_denied_phone_state_permission),
                        SplashInfoBoxID.TASK_PROGRESS_ERROR_DENIED_PHONE_STATE_PERMISSION.infoBoxID
                    )
                }

                /** PHONE STATE PERMISSION - END */

                /** ACCESS NETWORK STATE PERMISSION - START */

                SplashTasks.HAS_ACCESS_NETWORK_STATE_PERMISSION -> {
                    loadDeviceInfo(context,executeNextStep)
                }
                SplashTasks.NO_ACCESS_NETWORK_STATE_PERMISSION -> {
                    isRequestToGrantAccessNetworkPermission.postValue(true)
                }
                SplashTasks.GRANT_ACCESS_NETWORK_STATE_PERMISSION -> {
                    loadDeviceInfo(context,executeNextStep)
                }
                SplashTasks.DENY_ACCESS_NETWORK_STATE_PERMISSION -> {
                    infoBoxHandler.showErrorInfoBox(
                        router,
                        navController,
                        context?.getString(R.string.task_loading_error_denied_access_network_state_permission),
                        SplashInfoBoxID.TASK_PROGRESS_ERROR_DENIED_ACCESS_NETWORK_STATE_PERMISSION.infoBoxID
                    )
                }

                /** ACCESS NETWORK STATE PERMISSION - END */

                /** LOAD DEVICE INFO - START */

                SplashTasks.DEVICE_INFO_LOADED -> {
                    systemConfigurationAttempts = 0
                    requestToLoadSystemConfiguration(context, executeNextStep)
                }
                SplashTasks.DEVICE_INFO_NOT_LOADED -> {
                    infoBoxHandler.showErrorInfoBox(
                        router,
                        navController,
                        context?.getString(R.string.task_loading_error_device_info_not_loaded),
                        SplashInfoBoxID.TASK_PROGRESS_ERROR_DEVICE_INFO_NOT_LOADED.infoBoxID
                    )
                }

                /** LOAD DEVICE INFO - END */

                /** LOAD SYSTEM CONFIGURATION - START */

                SplashTasks.SYSTEM_CONFIGURATION_LOADED_SUCCESSFULLY ->{
                    systemConfigurationAttempts = 0
                    checkSession(context,executeNextStep)
                }
                SplashTasks.SYSTEM_CONFIGURATION_LOADING_FAILED -> {
                    systemConfigurationAttempts++

                    if(systemConfigurationAttempts >= 3){
                        infoBoxHandler.showErrorInfoBox(
                            router,
                            navController,
                            context?.getString(R.string.task_loading_error_system_configuration_not_loaded),
                            SplashInfoBoxID.TASK_PROGRESS_ERROR_SYSTEM_CONFIGURATION_NOT_LOADED.infoBoxID
                        )
                        /*showAppLoadingError(
                            context?.getString(R.string.task_loading_error_system_configuration_not_loaded),
                            SplashInfoBoxID.TASK_PROGRESS_ERROR_SYSTEM_CONFIGURATION_NOT_LOADED
                        )*/
                    }
                }

                /** LOAD SYSTEM CONFIGURATION - END */


                /** SESSION VALIDATION - START */

                SplashTasks.HAS_VALID_SESSION -> {
                    loadAuthData(context,executeNextStep)
                }
                SplashTasks.NO_VALID_SESSION -> {
                    loadAuthData(context,executeNextStep)
                    //isRedirectTpWalkthroughUI.postValue(true)
                }

                /** SESSION VALIDATION - END */

                /** LOAD USER INFO - START */

                SplashTasks.AUTH_DATA_LOADED -> {
                    if(appStates?.userAuth?.loginStatus!!){
                        isRedirectToHomeUI.postValue(true)
                    }else{
                        isRedirectTpWalkthroughUI.postValue(true)
                    }

                }
                SplashTasks.AUTH_DATA_NOT_LOADED -> {
                    authManger.invalidateUserAuthData()
                    isRedirectTpWalkthroughUI.postValue(true)
                }

                /** LOAD USER INFO - END */

            }
        }
    }

    /**
     * invoked when granting or denying requested app permissions
     *
     * @param requestCode Int requested permission code
     * @param permissions Array<out String> list of permissions which is granted or denied
     * @param grantResults IntArray list of results whether granted or denied
     * @param context Context application context
     */
    fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            phoneStatePermissionRequestCode -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    executeNextStep(context, SplashTasks.GRANT_PHONE_STATE_PERMISSION)
                }else{
                    executeNextStep(context, SplashTasks.DENY_PHONE_STATE_PERMISSION)
                }
            }
            accessNetworkStatePermissionRequestCode -> {
                if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    executeNextStep(context, SplashTasks.GRANT_ACCESS_NETWORK_STATE_PERMISSION)
                }else{
                    executeNextStep(context, SplashTasks.DENY_ACCESS_NETWORK_STATE_PERMISSION)
                }
            }
        }
    }

    /**
     * show currently executing task progress event
     *
     * @param text String progress message
     */
    private suspend fun setTaskProgress(text : String){
        withContext(Dispatchers.Main){
            taskProgressText.value = text
            delay(200)
        }
    }

    /**---------------------------------------------------------------------------------------------*
     * TASK PROGRESS - END
     *----------------------------------------------------------------------------------------------*/

    /**----------------------------------------------------------------------------------------------------------------*
     * EVENTS HANDLING - START
     *-----------------------------------------------------------------------------------------------------------------*/

    /**
     * callback function for executing when tapping on progress error message box
     *
     * @param errorCode Short error message id for identifying
     */
    private val onTapProgressErrorInfoBox : (errorCode : Short) -> Unit = {
        when(it){
            SplashInfoBoxID.TASK_PROGRESS_ERROR_KEYSTORE_GENERATION_FAILURE.infoBoxID -> {
                initializeDataLoading()
            }
            SplashInfoBoxID.TASK_PROGRESS_ERROR_DEVICE_IS_ROOTED.infoBoxID -> {
                shouldCloseApp.postValue(true)
            }
            SplashInfoBoxID.TASK_PROGRESS_ERROR_DENIED_PHONE_STATE_PERMISSION.infoBoxID -> {
                isRequestToGrantPhoneStatePermission.postValue(true)
            }
            SplashInfoBoxID.TASK_PROGRESS_ERROR_DEVICE_INFO_NOT_LOADED.infoBoxID -> {
                initializeDataLoading()
            }
            SplashInfoBoxID.TASK_PROGRESS_ERROR_SYSTEM_CONFIGURATION_NOT_LOADED.infoBoxID -> {
                initializeDataLoading()
            }
        }
    }

    /**----------------------------------------------------------------------------------------------------------------*
     * EVENTS HANDLING - END
     *-----------------------------------------------------------------------------------------------------------------*/

}
