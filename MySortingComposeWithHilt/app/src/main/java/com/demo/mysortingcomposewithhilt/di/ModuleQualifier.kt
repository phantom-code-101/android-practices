package com.demo.mysortingcomposewithhilt.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ModuleCurrencyDatabase

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ModuleCurrencyDao