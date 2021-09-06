package com.example.jogtracker.di.module

import com.example.data.repository.JogTrackerApiRepositoryImpl
import org.koin.core.module.Module
import org.koin.dsl.module

val repositoryModule: Module = module {

    single { JogTrackerApiRepositoryImpl(get(),get()) }
}



