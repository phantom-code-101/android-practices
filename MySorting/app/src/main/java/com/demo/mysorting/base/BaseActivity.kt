package com.demo.mysorting.base

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.demo.mysorting.delegate.OnActivityDelegate
import com.demo.mysorting.delegate.OnFragmentDelegate

open class BaseActivity : AppCompatActivity(), OnActivityDelegate {

    open fun getNavControllerLayoutId(): Int = -1

    open fun getNavGraphId(): Int = -1

    open fun getNavController(): NavController? {
        return getNavControllerLayoutId().takeIf { it > -1 }?.let {
            findNavController(getNavControllerLayoutId())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    override fun onPageAction(data: Map<String, Any>) = Unit

    override fun dispatchToFragment(@IdRes id: Int, bundle: Bundle?) {
        val delegate = getOnFragmentDelegateById(id)
        delegate?.onFragmentReceiveData(bundle)
    }

    override fun getOnFragmentDelegateById(@IdRes id: Int): OnFragmentDelegate? {
        val navHost = supportFragmentManager.findFragmentById(getNavControllerLayoutId())
        val currentDestinationId = getNavController()?.currentDestination?.id
        val childFragmentManager = navHost?.childFragmentManager
        val currentFragment = childFragmentManager?.primaryNavigationFragment
        return if (currentDestinationId == id) {
            currentFragment?.let { it as? OnFragmentDelegate }
        } else {
            null
        }
    }
}