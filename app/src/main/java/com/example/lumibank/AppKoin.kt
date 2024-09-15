package com.example.lumibank

import com.example.lumibank.splashScreen.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appKoin = module {
    viewModel { SplashViewModel(get()) }
}