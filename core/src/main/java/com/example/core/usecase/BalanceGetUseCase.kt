package com.example.core.usecase

import com.example.core.Resource
import com.example.core.model.response.BalanceGetResponseCore
import kotlinx.coroutines.flow.Flow

interface BalanceGetUseCase {
    suspend fun getBalance(token: String, accountNumber: String): Flow<Resource<BalanceGetResponseCore>>
}