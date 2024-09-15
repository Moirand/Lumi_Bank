package com.example.core.repository

import com.example.core.Resource
import com.example.core.model.response.BalanceGetResponseCore
import kotlinx.coroutines.flow.Flow

interface BalanceRepository {
    suspend fun getBalance(token: String, accountNumber: String): Flow<Resource<BalanceGetResponseCore>>
}