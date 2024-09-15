package com.example.feature_dashboard.viewModel

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dashboardViewModelKoin = module {
    viewModel { HomeViewModel(get(), get(), get()) }
}