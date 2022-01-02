package com.demo.mysortingcomposewithhilt.di

import android.content.Context
import androidx.room.Room
import com.demo.mysortingcomposewithhilt.database.CurrencyDao
import com.demo.mysortingcomposewithhilt.database.CurrencyDatabase
import com.demo.mysortingcomposewithhilt.ui.currency.CurrencyListViewModel
import com.demo.mysortingcomposewithhilt.ui.home.HomeViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
object AppModules {

    @ModuleCurrencyDatabase
    @Provides
    fun provideRoom(@ApplicationContext context: Context): CurrencyDatabase {
        return Room.databaseBuilder(context, CurrencyDatabase::class.java, "currency-info.db")
            .build()
    }

    @ModuleCurrencyDao
    @Provides
    fun provideCurrencyDao(@ModuleCurrencyDatabase db: CurrencyDatabase): CurrencyDao {
        return db.currencyDao
    }

    @Provides
    fun provideCurrencyListViewModel(): CurrencyListViewModel {
        return CurrencyListViewModel()
    }

    @Provides
    fun provideHomeViewModel(@ModuleCurrencyDao currencyDao: CurrencyDao): HomeViewModel {
        return HomeViewModel(currencyDao)
    }

}