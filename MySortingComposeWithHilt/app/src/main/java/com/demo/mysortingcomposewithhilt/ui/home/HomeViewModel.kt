package com.demo.mysortingcomposewithhilt.ui.home

import androidx.lifecycle.ViewModel
import com.demo.mysortingcomposewithhilt.database.CurrencyDao
import com.demo.mysortingcomposewithhilt.di.ModuleCurrencyDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    @ModuleCurrencyDao private val currencyDao: CurrencyDao
) : ViewModel() {

    fun getCurrencyList() {

    }

}