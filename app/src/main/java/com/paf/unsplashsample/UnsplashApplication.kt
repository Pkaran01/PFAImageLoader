package com.paf.unsplashsample

import android.app.Application
import com.paf.unsplashsample.di.listOfModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class UnsplashApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@UnsplashApplication)
            modules(listOfModules)
        }
    }
}