package com.example.jogtracker.di.module

import com.example.jogtracker.ui.screens.createjogfragment.CreateJogViewModel
import com.example.jogtracker.ui.screens.feedbackfragment.FeedbackViewModel
import com.example.jogtracker.ui.screens.jogslistfragment.JogsListViewModel
import com.example.jogtracker.ui.screens.loginfragment.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel(get(), get()) }
    viewModel { CreateJogViewModel(get()) }
    viewModel { JogsListViewModel(get()) }
    viewModel { FeedbackViewModel(get()) }

}