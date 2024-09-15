package com.example.core.usecase

import kotlinx.coroutines.flow.Flow

interface TokenGetUseCase {
    suspend fun getToken(): Flow<String?>
    suspend fun isTokenExpired(): Flow<Boolean>
}