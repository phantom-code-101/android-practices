package com.demo.shared.di

import javax.inject.Qualifier

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class DefaultOkHttpClient

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class DefaultRetrofitBuilder

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class HackerNewsApi

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class RandomUserApi