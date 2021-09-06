package com.example.jogtracker

import android.app.Application
import com.example.jogtracker.di.module.apiModule
import com.example.jogtracker.di.module.repositoryModule
import com.example.jogtracker.di.module.sharedPrefsModule
import com.example.jogtracker.di.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApp)
            modules(listOf(apiModule, repositoryModule, sharedPrefsModule, viewModelModule))
        }
    }
}