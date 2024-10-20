package com.example.core.repository

import com.example.core.Resource
import com.example.core.model.response.MutationDataCore
import com.example.core.model.response.MutationGetResponseCore
import com.example.core.model.response.MutationsGetResponseCore
import kotlinx.coroutines.flow.Flow

interface MutationRepository {
    suspend fun getAllMutations(token: String, accountNumber: String): Flow<Resource<MutationsGetResponseCore>>
    suspend fun getMutationsByDate(token: String, accountNumber: String, startDate: String, endDate: String, type: String): Flow<Resource<MutationsGetResponseCore>>
    suspend fun getMutationById(token: String, id: String): Flow<Resource<MutationGetResponseCore>>
}