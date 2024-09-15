package com.example.domain.usecase

import com.example.core.Resource
import com.example.core.model.response.BalanceGetResponseCore
import com.example.core.repository.BalanceRepository
import com.example.core.usecase.BalanceGetUseCase
import kotlinx.coroutines.flow.Flow

class BalanceGetUseCaseImpl(
    private val balanceRepository: BalanceRepository
) : BalanceGetUseCase {
    override suspend fun getBalance(token: String, accountNumber: String): Flow<Resource<BalanceGetResponseCore>> =
        balanceRepository.getBalance(token, accountNumber)
}