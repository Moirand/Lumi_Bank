package com.example.data.repository

import com.example.core.Resource
import com.example.core.datasource.RemoteDatasource
import com.example.core.model.response.MutationGetResponseCore
import com.example.core.repository.MutationRepository
import kotlinx.coroutines.flow.Flow

class MutationRepositoryImpl(
    private val remoteDatasource: RemoteDatasource
) : MutationRepository {
    override suspend fun getAllMutations(
        token: String,
        accountNumber: String
    ): Flow<Resource<MutationGetResponseCore>> =
        remoteDatasource.getAllMutations(token, accountNumber)

    override suspend fun getMutationsByDate(
        token: String,
        accountNumber: String,
        startDate: String,
        endDate: String,
        type: String
    ): Flow<Resource<MutationGetResponseCore>> =
        remoteDatasource.getMutationsByDate(token, accountNumber, startDate, endDate, type)
}