package com.example.core.usecase

import com.example.core.Resource
import com.example.core.model.request.LoginRequest
import com.example.core.model.response.LoginResponseCore
import kotlinx.coroutines.flow.Flow

interface LoginUseCase {
    suspend fun login(loginRequest: LoginRequest): Flow<Resource<LoginResponseCore>>
}