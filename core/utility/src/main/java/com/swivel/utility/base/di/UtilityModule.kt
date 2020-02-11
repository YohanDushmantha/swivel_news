package com.swivel.utility.base.di

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import com.swivel.models.base.AppConfig
import com.swivel.utility.device_info_manger.DeviceInfoManager
import com.swivel.utility.device_info_manger.IDeviceInfoManager
import com.swivel.utility.network_connection_manager.INetworkConnectionManager
import com.swivel.utility.network_connection_manager.NetworkConnectionManagerL
import com.swivel.utility.network_connection_manager.NetworkConnectionManagerM
import com.swivel.utility.permission_manager.IPermissionManager
import com.swivel.utility.permission_manager.PermissionManager
import com.swivel.utility.release_manager.IReleaseManager
import com.swivel.utility.release_manager.ReleaseManager
import com.swivel.utility.soft_keyboard_manager.ISoftKeyboardManager
import com.swivel.utility.soft_keyboard_manager.SoftKeyboardManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author Yohan Dushmantha
 * @class UtilityModule
 *
 * Provides utility module related dependencies for dagger
 */

@Module
class UtilityModule {

    @Singleton
    @Provides
    fun provideDeviceInfoManager() : IDeviceInfoManager{
        return DeviceInfoManager()
    }

    @Singleton
    @Provides
    fun providePermissionManager() : IPermissionManager{
        return PermissionManager()
    }

    /**---------------------------------------------------------------------------------------------*
     * NETWORK CONNECTION MANAGER - START
     *----------------------------------------------------------------------------------------------*/

    @Singleton
    @Provides
    fun provideNetworkConnectionManger(
        connectivityManager: ConnectivityManager,
        networkRequest: NetworkRequest
    ) : INetworkConnectionManager{
        return if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            NetworkConnectionManagerM(connectivityManager,networkRequest)
        }else{
            NetworkConnectionManagerL(connectivityManager, networkRequest)
        }

    }

    @Provides
    fun provideConnectivityManger(context: Context) : ConnectivityManager{
        return context.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    @Provides
    fun provideNetworkRequest() : NetworkRequest{
        return NetworkRequest.Builder().apply{
            addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
        }.build()
    }
    /**---------------------------------------------------------------------------------------------*
     * NETWORK CONNECTION MANAGER - END
     *----------------------------------------------------------------------------------------------*/

    @Singleton
    @Provides
    fun provideReleaseManager(appConfig: AppConfig) : IReleaseManager{
        return ReleaseManager(appConfig)
    }

    @Singleton
    @Provides
    fun provideSoftKeyboardManager(context: Context) : ISoftKeyboardManager{
        return SoftKeyboardManager(context)
    }
}