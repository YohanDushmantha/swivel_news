package com.swivel.utility.device_info_manger

import android.content.Context
import com.swivel.models.libs.utility.DeviceInfo

/**
 * @author Yohan Dushmantha
 * @class IDeviceInfoManager
 *
 * provide interface for DeviceInfoManger
 */
interface IDeviceInfoManager {

    /**
     * provide device info
     * @param context Context application context
     */
    fun info(context: Context?) : DeviceInfo?
}