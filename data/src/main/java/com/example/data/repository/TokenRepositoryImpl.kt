package com.example.data.repository

import com.example.core.datasource.LocalDatasource
import com.example.core.repository.TokenRepository
import kotlinx.coroutines.flow.Flow

class TokenRepositoryImpl(
    private val localDatasource: LocalDatasource
) : TokenRepository {
    override suspend fun saveToken(token: String) =
        localDatasource.saveToken(token)

    override suspend fun loadToken(): Flow<String?> =
        localDatasource.loadToken()

    override suspend fun deleteToken() =
        localDatasource.deleteToken()

    override suspend fun isTokenExpired(): Flow<Boolean> =
        localDatasource.isTokenExpired()
}