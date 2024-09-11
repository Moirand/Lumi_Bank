package com.example.data.repository

import com.example.core.Resource
import com.example.core.datasource.RemoteDatasource
import com.example.core.model.request.LoginRequest
import com.example.core.model.response.LoginResponseCore
import com.example.core.repository.AuthRepository
import kotlinx.coroutines.flow.Flow

class AuthRepositoryImpl(
    private val remoteDatasource: RemoteDatasource
) : AuthRepository {
    override suspend fun login(loginRequest: LoginRequest): Flow<Resource<LoginResponseCore>> =
        remoteDatasource.login(loginRequest)
}