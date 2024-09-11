package com.example.core.datasource

import com.example.core.Resource
import com.example.core.model.request.LoginRequest
import com.example.core.model.response.LoginResponseCore
import com.example.core.model.response.UserGetResponseCore
import kotlinx.coroutines.flow.Flow

interface RemoteDatasource {
    suspend fun login(loginRequest: LoginRequest): Flow<Resource<LoginResponseCore>>
    suspend fun getUserData(token: String): Flow<Resource<UserGetResponseCore>>
}