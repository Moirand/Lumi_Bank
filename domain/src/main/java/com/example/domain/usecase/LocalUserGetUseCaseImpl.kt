package com.example.domain.usecase

import com.example.core.repository.LocalUserRepository
import com.example.core.usecase.LocalUserGetUseCase
import kotlinx.coroutines.flow.Flow

class LocalUserGetUseCaseImpl(
    private val localUserRepository: LocalUserRepository
) : LocalUserGetUseCase {
    override suspend fun loadUserName(): Flow<String?> =
        localUserRepository.loadUserName()

    override suspend fun loadUserEmail(): Flow<String?> =
        localUserRepository.loadUserEmail()

    override suspend fun loadUserPhoneNumber(): Flow<String?> =
        localUserRepository.loadUserPhoneNumber()

    override suspend fun loadAccountNumber(): Flow<String?> =
        localUserRepository.loadAccountNumber()

    override suspend fun loadAccountPin(): Flow<String?> =
        localUserRepository.loadAccountPin()

    override suspend fun loadDateOfBirth(): Flow<String?> =
        localUserRepository.loadDateOfBirth()

    override suspend fun loadKtpNumber(): Flow<String?> =
        localUserRepository.loadKtpNumber()

    override suspend fun loadKtpPhoto(): Flow<String?> =
        localUserRepository.loadKtpPhoto()
}