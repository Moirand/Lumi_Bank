package com.example.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.core.datasource.LocalDatasource
import com.example.core.datasource.RemoteDatasource
import com.example.core.repository.AuthRepository
import com.example.core.repository.LocalUserRepository
import com.example.core.repository.RemoteUserRepository
import com.example.core.repository.TokenRepository
import com.example.core.usecase.LocalUserSetUseCase
import com.example.core.usecase.LoginUseCase
import com.example.core.usecase.RemoteUserGetUseCase
import com.example.core.usecase.TokenSetUseCase
import com.example.data.datasource.local.LocalDatasourceImpl
import com.example.data.datasource.local.dataStore
import com.example.data.datasource.remote.RemoteDatasourceImpl
import com.example.data.datasource.remote.network.ApiConfig
import com.example.data.datasource.remote.network.ApiService
import com.example.data.repository.AuthRepositoryImpl
import com.example.data.repository.LocalUserRepositoryImpl
import com.example.data.repository.RemoteUserRepositoryImpl
import com.example.data.repository.TokenRepositoryImpl
import com.example.domain.usecase.LocalUserSetUseCaseImpl
import com.example.domain.usecase.LoginUseCaseImpl
import com.example.domain.usecase.RemoteUserGetUseCaseImpl
import com.example.domain.usecase.TokenSetUseCaseImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val moduleKoin = module {
    // Use Case
    single<LocalUserSetUseCase> { LocalUserSetUseCaseImpl(get()) }
    single<LoginUseCase> { LoginUseCaseImpl(get()) }
    single<RemoteUserGetUseCase> { RemoteUserGetUseCaseImpl(get()) }
    single<TokenSetUseCase> { TokenSetUseCaseImpl(get()) }

    // Repository
    single<AuthRepository> { AuthRepositoryImpl(get()) }
    single<LocalUserRepository> { LocalUserRepositoryImpl(get()) }
    single<RemoteUserRepository> { RemoteUserRepositoryImpl(get()) }
    single<TokenRepository> { TokenRepositoryImpl(get()) }

    // Datasource
    single<LocalDatasource> { LocalDatasourceImpl(get()) }
    single<RemoteDatasource> { RemoteDatasourceImpl(get()) }
    single<ApiService> { ApiConfig.provideApiService(androidContext()) }

    single<DataStore<Preferences>> { androidContext().dataStore }
}