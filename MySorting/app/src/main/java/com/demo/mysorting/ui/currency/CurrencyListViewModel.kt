package com.demo.mysorting.ui.currency

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.demo.mysorting.database.CurrencyInfo
import com.demo.mysorting.utils.PageConstants
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable

class CurrencyListViewModel : ViewModel() {

    private val disposable = CompositeDisposable()
    private val _currencyInfoList = MutableLiveData<List<CurrencyInfo>>()
    val currencyInfoList: LiveData<List<CurrencyInfo>>
        get() = _currencyInfoList

    fun sorting(sortBy: String, list: List<CurrencyInfo>) {
        disposable.add(
            Observable.fromIterable(list)
                .toSortedList { currencyInfo, currencyInfo2 ->
                    when (sortBy) {
                        PageConstants.Sorting.BY_NAME -> currencyInfo.id.compareTo(currencyInfo2.id)
                        PageConstants.Sorting.BY_LENGTH -> {
                            currencyInfo.name?.let { currencyInfo2.name?.length?.compareTo(it.length) } ?: 1
                        }
                        else -> 1
                    }
                }
                .subscribe({
                    _currencyInfoList.postValue(it)
                }, {
                    _currencyInfoList.postValue(null)
                })
        )
    }

    fun stop() {
        disposable.clear()
    }
}