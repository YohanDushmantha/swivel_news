package com.swivel.utility.permission_manager

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

/**
 * @author Yohan Dushmantha
 * @class PermissionManager
 *
 * provide capability for checking and requesting app permissions
 */
class PermissionManager : IPermissionManager{

    override fun isPermissionGranted(context: Context, permission : String): Boolean {
        if(ContextCompat.checkSelfPermission(context,
                permission)
            != PackageManager.PERMISSION_GRANTED) { return false }
        return true
    }

}