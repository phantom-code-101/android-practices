package com.demo.mysorting.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.demo.mysorting.database.CurrencyDao
import com.demo.mysorting.database.CurrencyInfo
import com.demo.mysorting.model.Result
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class HomeViewModel(private val dataSource: CurrencyDao) : ViewModel() {

    private val disposable = CompositeDisposable()
    private val _sortingIsEnable = MutableLiveData<Boolean>()
    private val _currentInfoList = MutableLiveData<Result<Array<CurrencyInfo>>>()
    val sortingIsEnable: LiveData<Boolean>
        get() = _sortingIsEnable
    val currencyInfoList: LiveData<Result<Array<CurrencyInfo>>>
        get() = _currentInfoList

    fun getCurrencyList() {
        _currentInfoList.postValue(Result.Loading)
        disposable.add(
            dataSource
                .getCurrencyInfoList()
                .subscribeOn(Schedulers.io())
                .subscribe({ currencyInfoList ->
                    _currentInfoList.postValue(
                        Result.Success(data = currencyInfoList)
                    )
                    _sortingIsEnable.postValue(true)
                }, { throwable ->
                    _currentInfoList.postValue(
                        Result.Failure(throwable = throwable)
                    )
                    _sortingIsEnable.postValue(false)
                })
        )
    }

    fun setup(currencyInfoList: Array<CurrencyInfo>?) {
        currencyInfoList ?: return

        disposable.add(
            Completable.fromCallable { dataSource.setup(currencyInfoList) }
                .subscribeOn(Schedulers.io())
                .subscribe()
        )
    }

    fun stop() {
        disposable.clear()
    }

}