package com.example.jogtracker.di.module

import com.example.data.utils.SharedPrefsHelper
import org.koin.core.module.Module
import org.koin.dsl.module

val sharedPrefsModule: Module = module {
    single { SharedPrefsHelper(get()) }
}