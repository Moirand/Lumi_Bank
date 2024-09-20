package com.example.core.usecase

import com.example.core.Resource
import com.example.core.model.response.MutationGetResponseCore
import kotlinx.coroutines.flow.Flow

interface MutationGetUseCase {
    suspend fun getAllMutations(token: String, accountNumber: String): Flow<Resource<MutationGetResponseCore>>
    suspend fun getMutationsByDate(token: String, accountNumber: String, startDate: String, endDate: String, type: String): Flow<Resource<MutationGetResponseCore>>
}