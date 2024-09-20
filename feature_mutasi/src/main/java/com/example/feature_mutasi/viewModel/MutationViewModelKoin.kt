package com.example.feature_mutasi.viewModel

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mutationViewModelKoin = module {
    viewModel { MutationViewmodel(get(), get(), get()) }
}