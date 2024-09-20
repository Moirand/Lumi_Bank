package com.example.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.core.datasource.LocalDatasource
import com.example.core.datasource.RemoteDatasource
import com.example.core.repository.AuthRepository
import com.example.core.repository.BalanceRepository
import com.example.core.repository.LocalUserRepository
import com.example.core.repository.MutationRepository
import com.example.core.repository.RemoteUserRepository
import com.example.core.repository.TokenRepository
import com.example.core.usecase.BalanceGetUseCase
import com.example.core.usecase.LocalUserGetUseCase
import com.example.core.usecase.LocalUserSetUseCase
import com.example.core.usecase.LoginUseCase
import com.example.core.usecase.MutationGetUseCase
import com.example.core.usecase.RemoteUserGetUseCase
import com.example.core.usecase.TokenGetUseCase
import com.example.core.usecase.TokenSetUseCase
import com.example.data.datasource.local.LocalDatasourceImpl
import com.example.data.datasource.local.dataStore
import com.example.data.datasource.remote.RemoteDatasourceImpl
import com.example.data.datasource.remote.network.ApiConfig
import com.example.data.datasource.remote.network.ApiService
import com.example.data.repository.AuthRepositoryImpl
import com.example.data.repository.BalanceRepositoryImpl
import com.example.data.repository.LocalUserRepositoryImpl
import com.example.data.repository.MutationRepositoryImpl
import com.example.data.repository.RemoteUserRepositoryImpl
import com.example.data.repository.TokenRepositoryImpl
import com.example.domain.usecase.BalanceGetUseCaseImpl
import com.example.domain.usecase.LocalUserGetUseCaseImpl
import com.example.domain.usecase.LocalUserSetUseCaseImpl
import com.example.domain.usecase.LoginUseCaseImpl
import com.example.domain.usecase.MutationGetUseCaseImpl
import com.example.domain.usecase.RemoteUserGetUseCaseImpl
import com.example.domain.usecase.TokenGetUseCaseImpl
import com.example.domain.usecase.TokenSetUseCaseImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val moduleKoin = module {
    // Use Case
    single<BalanceGetUseCase> { BalanceGetUseCaseImpl(get()) }
    single<LocalUserGetUseCase> { LocalUserGetUseCaseImpl(get()) }
    single<LocalUserSetUseCase> { LocalUserSetUseCaseImpl(get()) }
    single<LoginUseCase> { LoginUseCaseImpl(get()) }
    single<MutationGetUseCase> { MutationGetUseCaseImpl(get()) }
    single<RemoteUserGetUseCase> { RemoteUserGetUseCaseImpl(get()) }
    single<TokenGetUseCase> { TokenGetUseCaseImpl(get()) }
    single<TokenSetUseCase> { TokenSetUseCaseImpl(get()) }

    // Repository
    single<AuthRepository> { AuthRepositoryImpl(get()) }
    single<BalanceRepository> { BalanceRepositoryImpl(get()) }
    single<LocalUserRepository> { LocalUserRepositoryImpl(get()) }
    single<MutationRepository> { MutationRepositoryImpl(get()) }
    single<RemoteUserRepository> { RemoteUserRepositoryImpl(get()) }
    single<TokenRepository> { TokenRepositoryImpl(get()) }

    // Datasource
    single<LocalDatasource> { LocalDatasourceImpl(get()) }
    single<RemoteDatasource> { RemoteDatasourceImpl(get()) }
    single<ApiService> { ApiConfig.provideApiService(androidContext()) }

    single<DataStore<Preferences>> { androidContext().dataStore }
}