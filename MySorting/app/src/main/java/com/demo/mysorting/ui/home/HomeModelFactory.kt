package com.demo.mysorting.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.demo.mysorting.database.CurrencyDao

class HomeModelFactory(
    private val dataSource: CurrencyDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unexpected model class.")
    }
}