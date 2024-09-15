package com.example.core.usecase

import kotlinx.coroutines.flow.Flow

interface LocalUserGetUseCase {
    suspend fun loadUserName(): Flow<String?>
    suspend fun loadUserEmail(): Flow<String?>
    suspend fun loadUserPhoneNumber(): Flow<String?>
    suspend fun loadAccountNumber(): Flow<String?>
    suspend fun loadAccountPin(): Flow<String?>
    suspend fun loadDateOfBirth(): Flow<String?>
    suspend fun loadKtpNumber(): Flow<String?>
    suspend fun loadKtpPhoto(): Flow<String?>
}