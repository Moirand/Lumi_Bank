package com.example.core.repository

import com.example.core.Resource
import com.example.core.model.request.LoginRequest
import com.example.core.model.response.LoginResponseCore
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(loginRequest: LoginRequest): Flow<Resource<LoginResponseCore>>
}