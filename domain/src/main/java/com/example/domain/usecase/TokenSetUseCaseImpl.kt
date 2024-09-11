package com.example.domain.usecase

import com.example.core.repository.TokenRepository
import com.example.core.usecase.TokenSetUseCase

class TokenSetUseCaseImpl(
    private val tokenRepository: TokenRepository,
) : TokenSetUseCase {
    override suspend fun saveToken(token: String) =
        tokenRepository.saveToken(token)

    override suspend fun deleteToken() =
        tokenRepository.deleteToken()
}