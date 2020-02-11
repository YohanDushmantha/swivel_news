package com.swivel.utility.permission_manager

import android.content.Context

/**
 * @author Yohan Dushmantha
 * @class IPermissionManager
 *
 * provide interface for PermissionManager
 */
interface IPermissionManager {

    fun isPermissionGranted(context: Context, permission : String) : Boolean
}