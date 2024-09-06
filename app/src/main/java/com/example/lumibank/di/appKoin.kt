package com.example.lumibank.di

import androidx.appcompat.app.AppCompatActivity
import com.example.common.NavigationHandler
import org.koin.dsl.module

val appKoin = module {
    single<NavigationHandler> { get<AppCompatActivity>() as NavigationHandler }
}