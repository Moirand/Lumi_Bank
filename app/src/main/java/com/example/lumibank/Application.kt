package com.example.lumibank

import android.app.Application
import com.example.di.moduleKoin
import com.example.feature_auth.viewModel.authViewModelKoin
import com.example.feature_dashboard.viewModel.dashboardViewModelKoin
import com.example.feature_mutasi.viewModel.mutationViewModelKoin
import com.example.feature_transfer.viewModel.transferViewModelKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@Application)
            modules(
                appKoin,
                moduleKoin,
                authViewModelKoin,
                dashboardViewModelKoin,
                mutationViewModelKoin,
                transferViewModelKoin
            )
        }
    }
}