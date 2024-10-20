package com.example.domain.usecase

import com.example.core.Resource
import com.example.core.model.request.TransferRequest
import com.example.core.model.response.TransferResponseCore
import com.example.core.repository.TransferRepository
import com.example.core.usecase.TransferUseCase
import kotlinx.coroutines.flow.Flow

class TransferUseCaseImpl(
    private val transferRepository: TransferRepository
) : TransferUseCase {
    override suspend fun transfer(
        token: String,
        transferRequest: TransferRequest
    ): Flow<Resource<TransferResponseCore>> =
        transferRepository.transfer(token, transferRequest)
}