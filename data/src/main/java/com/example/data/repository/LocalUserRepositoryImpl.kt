package com.example.data.repository

import com.example.core.datasource.LocalDatasource
import com.example.core.repository.LocalUserRepository
import kotlinx.coroutines.flow.Flow

class LocalUserRepositoryImpl(
    private val localDatasource: LocalDatasource
) : LocalUserRepository {
    override suspend fun loadUserName(): Flow<String?> =
        localDatasource.loadUserName()

    override suspend fun loadUserEmail(): Flow<String?> =
        localDatasource.loadUserEmail()

    override suspend fun loadUserPhoneNumber(): Flow<String?> =
        localDatasource.loadUserPhoneNumber()

    override suspend fun loadAccountNumber(): Flow<String?> =
        localDatasource.loadAccountNumber()

    override suspend fun loadAccountPin(): Flow<String?> =
        localDatasource.loadAccountPin()

    override suspend fun loadDateOfBirth(): Flow<String?> =
        localDatasource.loadDateOfBirth()

    override suspend fun loadKtpNumber(): Flow<String?> =
        localDatasource.loadKtpNumber()

    override suspend fun loadKtpPhoto(): Flow<String?> =
        localDatasource.loadKtpPhoto()

    override suspend fun saveUserName(userName: String) =
        localDatasource.saveUserName(userName)

    override suspend fun saveUserEmail(userEmail: String) =
        localDatasource.saveUserEmail(userEmail)

    override suspend fun saveUserPhoneNumber(userPhoneNumber: String) =
        localDatasource.saveUserPhoneNumber(userPhoneNumber)

    override suspend fun saveAccountNumber(accountNumber: String) =
        localDatasource.saveAccountNumber(accountNumber)

    override suspend fun saveAccountPin(accountPin: String) =
        localDatasource.saveAccountPin(accountPin)

    override suspend fun saveDateOfBirth(dateOfBirth: String) =
        localDatasource.saveDateOfBirth(dateOfBirth)

    override suspend fun saveKtpNumber(ktpNumber: String) =
        localDatasource.saveKtpNumber(ktpNumber)

    override suspend fun saveKtpPhoto(ktpPhoto: String) =
        localDatasource.saveKtpPhoto(ktpPhoto)

    override suspend fun deleteUserData() =
        localDatasource.deleteUserData()
}