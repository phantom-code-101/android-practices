package com.demo.appsecurepractices

import android.content.Context
import android.os.Build
import android.provider.Settings
import java.util.*

object DeviceInfo {

    fun isEmulator(): Boolean {
        return (Build.FINGERPRINT.startsWith("generic")
            || Build.FINGERPRINT.startsWith("unknown")
            || Build.MODEL.contains("google_sdk")
            || Build.MODEL.lowercase(Locale.ROOT).contains("droid4x")
            || Build.MODEL.contains("Emulator")
            || Build.MODEL.contains("Android SDK built for x86")
            || Build.MANUFACTURER.contains("Genymotion")
            || Build.HARDWARE.contains("goldfish")
            || Build.HARDWARE.contains("ranchu")
            || Build.HARDWARE.contains("vbox86")
            || Build.PRODUCT.contains("sdk")
            || Build.PRODUCT.contains("google_sdk")
            || Build.PRODUCT.contains("sdk_google")
            || Build.PRODUCT.contains("sdk_x86")
            || Build.PRODUCT.contains("vbox86p")
            || Build.PRODUCT.contains("emulator")
            || Build.PRODUCT.contains("simulator")
            || Build.BOARD.lowercase(Locale.ROOT).contains("nox")
            || Build.BOOTLOADER.lowercase(Locale.ROOT).contains("nox")
            || Build.HARDWARE.lowercase(Locale.ROOT).contains("nox")
            || Build.PRODUCT.lowercase(Locale.ROOT).contains("nox")
            || Build.SERIAL.lowercase(Locale.ROOT).contains("nox")
            || Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic"))
    }

    fun isDevMode(context: Context): Boolean {
        return when {
            Build.VERSION.SDK_INT == Build.VERSION_CODES.JELLY_BEAN -> {
                Settings.Global.getInt(
                    context.contentResolver,
                    Settings.Secure.DEVELOPMENT_SETTINGS_ENABLED,
                    0
                ) == 1
            }
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 -> {
                Settings.Global.getInt(
                    context.contentResolver,
                    Settings.Global.DEVELOPMENT_SETTINGS_ENABLED,
                    0
                ) == 1
            }
            else -> Settings.Global.getInt(context.contentResolver, Settings.Global.ADB_ENABLED, 0) == 1
        }
    }

    fun getUniqueId(context: Context): String {
        return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID) ?: "Unknown"
    }

}