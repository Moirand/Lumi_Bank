package com.example.domain.usecase

import com.example.core.repository.LocalUserRepository
import com.example.core.usecase.LocalUserSetUseCase

class LocalUserSetUseCaseImpl(
    private val localUserRepository: LocalUserRepository
) : LocalUserSetUseCase {
    override suspend fun saveUserName(userName: String) =
        localUserRepository.saveUserName(userName)

    override suspend fun saveUserEmail(userEmail: String) =
        localUserRepository.saveUserEmail(userEmail)

    override suspend fun saveUserPhoneNumber(userPhoneNumber: String) =
        localUserRepository.saveUserPhoneNumber(userPhoneNumber)

    override suspend fun saveAccountNumber(accountNumber: String) =
        localUserRepository.saveAccountNumber(accountNumber)

    override suspend fun saveAccountPin(accountPin: String) =
        localUserRepository.saveAccountPin(accountPin)

    override suspend fun saveDateOfBirth(dateOfBirth: String) =
        localUserRepository.saveDateOfBirth(dateOfBirth)

    override suspend fun saveKtpNumber(ktpNumber: String) =
        localUserRepository.saveKtpNumber(ktpNumber)

    override suspend fun saveKtpPhoto(ktpPhoto: String) =
        localUserRepository.saveKtpPhoto(ktpPhoto)

    override suspend fun deleteUserData() =
        localUserRepository.deleteUserData()
}