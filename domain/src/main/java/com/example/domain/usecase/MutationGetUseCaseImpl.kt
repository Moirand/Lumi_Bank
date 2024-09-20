package com.example.domain.usecase

import com.example.core.Resource
import com.example.core.model.response.MutationGetResponseCore
import com.example.core.repository.MutationRepository
import com.example.core.usecase.MutationGetUseCase
import kotlinx.coroutines.flow.Flow

class MutationGetUseCaseImpl(
    private val mutationRepository: MutationRepository
) : MutationGetUseCase {
    override suspend fun getAllMutations(
        token: String,
        accountNumber: String
    ): Flow<Resource<MutationGetResponseCore>> {
        return mutationRepository.getAllMutations(token, accountNumber)
    }

    override suspend fun getMutationsByDate(
        token: String,
        accountNumber: String,
        startDate: String,
        endDate: String,
        type: String
    ): Flow<Resource<MutationGetResponseCore>> =
        mutationRepository.getMutationsByDate(token, accountNumber, startDate, endDate, type)
}