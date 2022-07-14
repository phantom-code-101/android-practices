package com.demo.appsecurepractices

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.demo.appsecurepractices.databinding.ActivityMainBinding
import com.scottyab.rootbeer.RootBeer

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var deviceInfo = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        deviceInfo = getEmulatorOrRealDevice()
        deviceInfo += getDevMode()
        deviceInfo += getRooted()
        deviceInfo += getUniqueId()

        binding.tvDeviceInfo.text = deviceInfo
    }

    private fun getRooted(): String {
        val rootBeer = RootBeer(this)
        return if (rootBeer.isRooted || rootBeer.isRootedWithBusyBoxCheck) "Rooted \n" else "Protected \n"
    }

    private fun getEmulatorOrRealDevice(): String {
        return if (DeviceInfo.isEmulator()) "Emulator \n" else "Real Device \n"
    }

    private fun getDevMode(): String {
        return if (DeviceInfo.isDevMode(this)) "DebugMode (Opening) \n" else "DebugMode (Colsed) \n"
    }

    private fun getUniqueId(): String {
        return "Android Id (Unique) : " + DeviceInfo.getUniqueId(this)
    }
}