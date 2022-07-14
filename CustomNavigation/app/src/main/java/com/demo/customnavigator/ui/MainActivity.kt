package com.demo.customnavigator.ui

import android.os.Bundle
import com.demo.core.base.BaseActivity
import com.demo.customnavigator.R
import com.demo.customnavigator.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun getNavControllerLayoutId(): Int = R.id.fragmentContainer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setListener()
    }

    private fun setListener() {
        binding.bottomNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.landing -> {
                    getNavController()?.navigate(R.id.homeFragment)
                }
                R.id.markets -> {
                    getNavController()?.navigate(R.id.marketsFragment)
                }
                R.id.trading -> {
                    getNavController()?.navigate(R.id.tradingFragment)
                }
                R.id.liquidassets -> {
                    getNavController()?.navigate(R.id.liquidassetsFragment)
                }
            }

            true
        }
    }
}