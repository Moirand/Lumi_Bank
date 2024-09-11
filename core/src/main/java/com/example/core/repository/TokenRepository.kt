package com.example.core.repository

import kotlinx.coroutines.flow.Flow

interface TokenRepository {
    suspend fun saveToken(token: String)
    suspend fun loadToken(): Flow<String?>
    suspend fun deleteToken()
    suspend fun isTokenExpired(): Flow<Boolean>
}