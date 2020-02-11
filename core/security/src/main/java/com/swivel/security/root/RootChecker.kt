package com.swivel.security.root

import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import java.io.BufferedReader
import java.io.File
import java.io.IOException
import java.io.InputStreamReader

/**
 * @author Yohan Dushmantha
 * @class RootChecker
 *
 * provide facility for checking whether device is rooted or not
 */
class RootChecker {

    fun isRooted(context: Context): Boolean {
        val isTestBuild = isTestBuild()
        val hasSuperuserAPK = hasSuperuserAPK()
        val hasChainfiresupersu = hasChainfiresupersu(context)
        val hasSU = hasSU()
        Log.d("RootChecker", "isTestBuild: $isTestBuild hasSuperuserAPK: $hasSuperuserAPK hasChainfiresupersu: $hasChainfiresupersu hasSU: $hasSU")
        return isTestBuild || hasSuperuserAPK || hasChainfiresupersu || hasSU
    }

    private fun isTestBuild(): Boolean {
        val buildTags = android.os.Build.TAGS
        return buildTags != null && buildTags.contains("test-keys")
    }

    private fun hasSuperuserAPK(): Boolean {
        try {
            val file = File("/system/app/Superuser.apk")
            return file.exists()
        } catch (e: Exception) {
            return false
        }

    }

    private fun hasChainfiresupersu(context: Context): Boolean {
        return isPackageInstalled("eu.chainfire.supersu", context)
    }

    private fun hasSU(): Boolean {
        return findBinary("su") || executeCommand(arrayOf("/system/xbin/which", "su")) || executeCommand(arrayOf("which", "su"))
    }

    private fun isPackageInstalled(packagename: String, context: Context): Boolean {
        val pm = context.getPackageManager()
        try {
            pm.getPackageInfo(packagename, PackageManager.GET_ACTIVITIES)
            return true
        } catch (e: PackageManager.NameNotFoundException) {
            return false
        }

    }

    private fun findBinary(binaryName: String): Boolean {
        val places = arrayOf(
            "/sbin/",
            "/system/bin/",
            "/system/xbin/",
            "/data/local/xbin/",
            "/data/local/bin/",
            "/system/sd/xbin/",
            "/system/bin/failsafe/",
            "/data/local/")
        for (where in places) {
            if (File(where + binaryName).exists()) {
                return true
            }
        }
        return false
    }

    private fun executeCommand(command: Array<String>): Boolean {
        var localProcess: Process? = null
        var inReader : BufferedReader? = null
        try {
            localProcess = Runtime.getRuntime().exec(command)
            inReader = BufferedReader(InputStreamReader(localProcess?.inputStream))
            return inReader.readLine() != null
        } catch (e: Exception) {
            return false
        } finally {
            localProcess?.destroy()
            if (inReader != null) {
                try {
                    inReader.close()
                } catch (e: IOException) {
                    Log.e("RootChecker", e.message)
                    e.printStackTrace()
                }

            }
        }
    }

}