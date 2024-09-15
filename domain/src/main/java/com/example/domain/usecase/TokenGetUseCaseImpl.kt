package com.example.domain.usecase

import com.example.core.repository.TokenRepository
import com.example.core.usecase.TokenGetUseCase
import kotlinx.coroutines.flow.Flow

class TokenGetUseCaseImpl(
    private val tokenRepository: TokenRepository
) : TokenGetUseCase {
    override suspend fun getToken(): Flow<String?> =
        tokenRepository.loadToken()

    override suspend fun isTokenExpired(): Flow<Boolean> =
        tokenRepository.isTokenExpired()
}