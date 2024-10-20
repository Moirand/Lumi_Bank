package com.example.data.repository

import com.example.core.Resource
import com.example.core.datasource.RemoteDatasource
import com.example.core.model.response.MutationDataCore
import com.example.core.model.response.MutationGetResponseCore
import com.example.core.model.response.MutationsGetResponseCore
import com.example.core.repository.MutationRepository
import kotlinx.coroutines.flow.Flow

class MutationRepositoryImpl(
    private val remoteDatasource: RemoteDatasource
) : MutationRepository {
    override suspend fun getAllMutations(
        token: String,
        accountNumber: String
    ): Flow<Resource<MutationsGetResponseCore>> =
        remoteDatasource.getAllMutations(token, accountNumber)

    override suspend fun getMutationsByDate(
        token: String,
        accountNumber: String,
        startDate: String,
        endDate: String,
        type: String
    ): Flow<Resource<MutationsGetResponseCore>> =
        remoteDatasource.getMutationsByDate(token, accountNumber, startDate, endDate, type)

    override suspend fun getMutationById(
        token: String,
        id: String
    ): Flow<Resource<MutationGetResponseCore>> =
        remoteDatasource.getMutationById(token, id)
}