package com.demo.mysortingcomposewithhilt.database

import androidx.room.*
import io.reactivex.rxjava3.core.Flowable

@Dao
interface CurrencyDao {

    @Transaction
    fun setup(currencyInfoList: Array<CurrencyInfo>) {
        deleteAllCurrencyInfo()
        insertCurrencyInfoList(currencyInfoList)
    }

    @Query("DELETE FROM CurrencyInfo")
    fun deleteAllCurrencyInfo()

    @Insert
    fun insertCurrencyInfoList(currencyInfo: Array<CurrencyInfo>)

    @Query("SELECT * FROM CurrencyInfo")
    fun getCurrencyInfoList(): Flowable<Array<CurrencyInfo>>

}