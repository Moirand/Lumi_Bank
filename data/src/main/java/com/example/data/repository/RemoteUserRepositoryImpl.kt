package com.example.data.repository

import com.example.core.Resource
import com.example.core.datasource.RemoteDatasource
import com.example.core.model.response.UserGetResponseCore
import com.example.core.repository.RemoteUserRepository
import kotlinx.coroutines.flow.Flow

class RemoteUserRepositoryImpl(
    private val remoteDatasource: RemoteDatasource
) : RemoteUserRepository {
    override suspend fun getUserData(token: String): Flow<Resource<UserGetResponseCore>> =
        remoteDatasource.getUserData(token)
}