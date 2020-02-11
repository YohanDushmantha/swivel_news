package com.swivel.utility.device_info_manger

import android.content.Context
import android.os.Build
import android.telephony.TelephonyManager
import androidx.appcompat.app.AppCompatActivity
import com.swivel.utility.BuildConfig
import com.swivel.models.libs.utility.DeviceInfo

/**
 * @author Yohan Dushmantha
 * @class DeviceInfoManager
 *
 * provide device information
 */
class DeviceInfoManager  : IDeviceInfoManager {

    override fun info(context: Context?) : DeviceInfo? {
        return context?.let {
            DeviceInfo(
                getIMEI(context),
                getDeviceOS(),
                getAppVersion(),
                getBuildNumber(),
                getDeviceModel()
            )
        }
    }

    private fun getIMEI(context: Context): String {
        return try {
            val telephonyManager = context.getSystemService(AppCompatActivity.TELEPHONY_SERVICE) as TelephonyManager
            if (android.os.Build.VERSION.SDK_INT >= 26) {
                telephonyManager.imei
            } else {
                telephonyManager.deviceId
            }
        }catch (ex : SecurityException){
            ex.printStackTrace()
            "No Sim Card"
        }
    }

    private fun getDeviceModel(): String {
        val manufacturer : String = Build.MANUFACTURER
        val model : String = Build.MODEL

        if(model.startsWith(manufacturer)){
            return model.toUpperCase()
        }
        return "$manufacturer $model".toUpperCase()
    }

    private fun getDeviceOS() : Int {
        return Build.VERSION.SDK_INT
    }

    private fun getAppVersion() : String {
        return BuildConfig.VERSION_NAME
    }

    private fun getBuildNumber() : Int {
        return BuildConfig.VERSION_CODE
    }

}