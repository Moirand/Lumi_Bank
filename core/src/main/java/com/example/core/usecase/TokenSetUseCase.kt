package com.example.core.usecase

interface TokenSetUseCase {
    suspend fun saveToken(token: String)
    suspend fun deleteToken()
}