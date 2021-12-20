package com.demo.mysorting

import android.app.Application
import com.demo.mysorting.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {

            // Pass the context to koin so that other module to reference
            androidContext(this@MyApplication)

            // Initialize all types of dependencies
            modules(appModule)
        }
    }

}