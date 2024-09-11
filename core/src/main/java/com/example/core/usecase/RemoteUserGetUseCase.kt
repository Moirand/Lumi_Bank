package com.example.core.usecase

import com.example.core.Resource
import com.example.core.model.response.UserGetResponseCore
import kotlinx.coroutines.flow.Flow

interface RemoteUserGetUseCase {
    suspend fun getUserData(token: String): Flow<Resource<UserGetResponseCore>>
}