package com.example.core.repository

import com.example.core.Resource
import com.example.core.model.request.TransferRequest
import com.example.core.model.response.TransferResponseCore
import kotlinx.coroutines.flow.Flow

interface TransferRepository {
    suspend fun transfer(token: String, transferRequest: TransferRequest): Flow<Resource<TransferResponseCore>>
}