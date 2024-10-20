package com.example.data.repository

import com.example.core.Resource
import com.example.core.datasource.RemoteDatasource
import com.example.core.model.request.TransferRequest
import com.example.core.model.response.TransferResponseCore
import com.example.core.repository.TransferRepository
import kotlinx.coroutines.flow.Flow

class TransferRepositoryImpl(
    private val remoteDatasource: RemoteDatasource
) : TransferRepository {
    override suspend fun transfer(
        token: String,
        transferRequest: TransferRequest
    ): Flow<Resource<TransferResponseCore>> =
        remoteDatasource.transfer(token, transferRequest)
}