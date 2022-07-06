package com.demo.landing.di

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.demo.core.di.NetworkInjection
import com.demo.core.helper.ServerConstants
import com.demo.core.viewmodel.ViewModelFactory
import com.demo.landing.network.CryptoCurrencyApi
import com.demo.landing.network.CryptoCurrencyDataSource
import com.demo.landing.ui.HomeViewModel

object CryptoCurrencyInjection {

    // Crypto Currency Data Sources provided by landing feature
    private fun provideCryptoCurrencyDataSource(): CryptoCurrencyDataSource {
        return CryptoCurrencyDataSource(
            NetworkInjection.provideRetrofit(
                ServerConstants.CURRENCY_SANDBOX_API_DOMAIN,
                CryptoCurrencyApi::class.java
            )
        )
    }

    fun <T> provideHomeViewModel(owner: T): HomeViewModel where T : ViewModelStoreOwner {
        val dataSource = provideCryptoCurrencyDataSource()
        val factory = ViewModelFactory.create(dataSource)
        return ViewModelProvider(owner, factory).get(HomeViewModel::class.java)
    }

}