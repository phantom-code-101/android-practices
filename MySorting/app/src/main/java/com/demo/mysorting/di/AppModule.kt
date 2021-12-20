package com.demo.mysorting.di

import android.content.Context
import androidx.room.Room
import com.demo.mysorting.database.CurrencyDatabase
import com.demo.mysorting.database.CurrencyDao
import com.demo.mysorting.ui.currency.CurrencyListViewModel
import com.demo.mysorting.ui.home.HomeModelFactory
import com.demo.mysorting.ui.home.HomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    fun provideDatabase(context: Context): CurrencyDatabase {
        return Room.databaseBuilder(context, CurrencyDatabase::class.java, "currency-info.db")
            .build()
    }

    fun provideCurrencyDao(db: CurrencyDatabase): CurrencyDao {
        return db.currencyDao
    }

    single { provideDatabase(androidContext()) }
    single { provideCurrencyDao(get()) }

    factory {
        HomeModelFactory(get())
    }

    viewModel {
        HomeViewModel(get())
    }

    viewModel {
        CurrencyListViewModel()
    }
}