package com.example.domain.usecase

import com.example.core.Resource
import com.example.core.model.response.MutationDataCore
import com.example.core.model.response.MutationGetResponseCore
import com.example.core.model.response.MutationsGetResponseCore
import com.example.core.repository.MutationRepository
import com.example.core.usecase.MutationGetUseCase
import kotlinx.coroutines.flow.Flow

class MutationGetUseCaseImpl(
    private val mutationRepository: MutationRepository
) : MutationGetUseCase {
    override suspend fun getAllMutations(
        token: String,
        accountNumber: String
    ): Flow<Resource<MutationsGetResponseCore>> {
        return mutationRepository.getAllMutations(token, accountNumber)
    }

    override suspend fun getMutationsByDate(
        token: String,
        accountNumber: String,
        startDate: String,
        endDate: String,
        type: String
    ): Flow<Resource<MutationsGetResponseCore>> =
        mutationRepository.getMutationsByDate(token, accountNumber, startDate, endDate, type)

    override suspend fun getMutationById(
        token: String,
        id: String
    ): Flow<Resource<MutationGetResponseCore>> =
        mutationRepository.getMutationById(token, id)
}