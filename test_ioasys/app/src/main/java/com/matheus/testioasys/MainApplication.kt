package com.matheus.testioasys

import android.app.Application
import com.matheus.testioasys.di.dataModule
import com.matheus.testioasys.di.signInModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            modules(dataModule, signInModule)
        }
    }
}