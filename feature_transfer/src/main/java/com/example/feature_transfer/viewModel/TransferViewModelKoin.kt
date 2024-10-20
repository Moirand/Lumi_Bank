package com.example.feature_transfer.viewModel

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val transferViewModelKoin = module {
    viewModel { TransferViewModel(get(), get(), get(), get(), get(), get(), get()) }
}