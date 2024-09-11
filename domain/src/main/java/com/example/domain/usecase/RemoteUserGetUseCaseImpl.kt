package com.example.domain.usecase

import com.example.core.Resource
import com.example.core.model.response.UserGetResponseCore
import com.example.core.repository.RemoteUserRepository
import com.example.core.usecase.RemoteUserGetUseCase
import kotlinx.coroutines.flow.Flow

class RemoteUserGetUseCaseImpl(
    private val remoteUserRepository: RemoteUserRepository
) : RemoteUserGetUseCase {
    override suspend fun getUserData(token: String): Flow<Resource<UserGetResponseCore>> =
        remoteUserRepository.getUserData(token)
}