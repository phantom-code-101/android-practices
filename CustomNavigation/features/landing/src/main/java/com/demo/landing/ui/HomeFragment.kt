package com.demo.landing.ui

import com.demo.core.base.BaseFragment
import com.demo.landing.R
import com.demo.landing.databinding.FragmentHomeBinding
import com.demo.landing.di.CryptoCurrencyInjection

class HomeFragment: BaseFragment<FragmentHomeBinding>() {

    private lateinit var homeViewModel: HomeViewModel

    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun onCreateDataBinding(viewDataBinding: FragmentHomeBinding) {
        super.onCreateDataBinding(viewDataBinding)
        homeViewModel = CryptoCurrencyInjection.provideHomeViewModel(this)
    }

}