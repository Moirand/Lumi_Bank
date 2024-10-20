package com.example.core.usecase

import com.example.core.Resource
import com.example.core.model.request.TransferRequest
import com.example.core.model.response.TransferResponseCore
import kotlinx.coroutines.flow.Flow

interface TransferUseCase {
    suspend fun transfer(token: String, transferRequest: TransferRequest): Flow<Resource<TransferResponseCore>>
}