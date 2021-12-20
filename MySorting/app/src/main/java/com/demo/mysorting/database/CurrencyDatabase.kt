package com.demo.mysorting.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CurrencyInfo::class], version = 1, exportSchema = false)
abstract class CurrencyDatabase : RoomDatabase() {
    abstract val currencyDao: CurrencyDao
}