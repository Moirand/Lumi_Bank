package com.example.domain.usecase

import com.example.core.Resource
import com.example.core.model.request.LoginRequest
import com.example.core.model.response.LoginResponseCore
import com.example.core.repository.AuthRepository
import com.example.core.usecase.LoginUseCase
import kotlinx.coroutines.flow.Flow

class LoginUseCaseImpl(
    private val authRepository: AuthRepository
) : LoginUseCase {
    override suspend fun login(loginRequest: LoginRequest): Flow<Resource<LoginResponseCore>> =
        authRepository.login(loginRequest)
}