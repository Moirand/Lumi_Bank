package com.example.data.repository

import com.example.core.Resource
import com.example.core.datasource.RemoteDatasource
import com.example.core.model.response.BalanceGetResponseCore
import com.example.core.repository.BalanceRepository
import kotlinx.coroutines.flow.Flow

class BalanceRepositoryImpl(
    private val remoteDatasource: RemoteDatasource
) : BalanceRepository {
    override suspend fun getBalance(token: String, accountNumber: String): Flow<Resource<BalanceGetResponseCore>> =
        remoteDatasource.getBalance(token, accountNumber)
}