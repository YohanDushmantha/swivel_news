package com.swivel.news.main

import androidx.lifecycle.MutableLiveData
import com.swivel.core.ui.BaseViewModel
import com.swivel.models.base.AppConfig
import com.swivel.models.features.IBaseDeepLinkArguments
import com.swivel.news.BuildConfig
import com.swivel.utility.network_connection_manager.INetworkConnectionManager
import timber.log.Timber
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val appConfig: AppConfig,
    private val networkConnectionManager : INetworkConnectionManager
) : BaseViewModel() {

    val isInternetAlive = MutableLiveData<Boolean>()

    val networkChanged : (isAlive : Boolean) -> Unit = {
        Timber.i("YC -> NC -> Main View Model network changed $it")
        isInternetAlive.postValue(it)
    }

    override fun initViewArguments(deepLinkArguments: IBaseDeepLinkArguments?) {
        networkConnectionManager.registerNetworkConnectionManager(networkChanged)
    }

    override fun onCleared() {
        super.onCleared()
        networkConnectionManager.unregisterNetworkConnectionManager()
    }

    /**---------------------------------------------------------------------------------------------*
     * LOAD ENVIRONMENTAL RELATED CONFIGURATION'S TO APP - START
     *----------------------------------------------------------------------------------------------*/

    /**
     * load build config data into app config for using every where of the application
     */
    fun loadAppConfigs(){
        appConfig.apply {
            /**
             * COMMON PROPERTIES
             */
            debug = BuildConfig.DEBUG
            applicationId = BuildConfig.APPLICATION_ID
            buildType = BuildConfig.BUILD_TYPE
            flavor = BuildConfig.FLAVOR
            versionCode = BuildConfig.VERSION_CODE
            versionName = BuildConfig.VERSION_NAME

            Timber.i("YD -> APP CONFIGS -> applicationId inside ob -> $applicationId")

            /**
             * SWIVEL BACK END SERVICE END POINTS
             */
            newsServiceEndPoint =
                BuildConfig.NEWS_SERVICE_ENDPOINT
            mapServiceEndPoint =
                BuildConfig.MAP_SERVICE_ENDPOINT
            notificationEndPoint =
                BuildConfig.NOTIFICATION_ENDPOINT

            Timber.i("YD -> APP CONFIGS -> notificationEndPoint inside ob -> $notificationEndPoint")

            /**
             * S3 BUCKET END POINTS AND DATA
             */
            s3BucketEndPoint =
                BuildConfig.S3_BUCKET_ENDPOINT
            s3Bucket = BuildConfig.S3_BUCKET
        }
    }

    /**---------------------------------------------------------------------------------------------*
     * LOAD ENVIRONMENTAL RELATED CONFIGURATION'S TO APP - END
     *----------------------------------------------------------------------------------------------*/
}