package com.example.feature_auth.viewModel

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authViewModelKoin = module {
    viewModel { LoginViewModel(get(), get(), get(), get()) }
}