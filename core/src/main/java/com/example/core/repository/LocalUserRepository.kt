package com.example.core.repository

import kotlinx.coroutines.flow.Flow

interface LocalUserRepository {
    suspend fun loadUserName(): Flow<String?>
    suspend fun loadUserEmail(): Flow<String?>
    suspend fun loadUserPhoneNumber(): Flow<String?>
    suspend fun loadAccountNumber(): Flow<String?>
    suspend fun loadAccountPin(): Flow<String?>
    suspend fun loadDateOfBirth(): Flow<String?>
    suspend fun loadKtpNumber(): Flow<String?>
    suspend fun loadKtpPhoto(): Flow<String?>
    suspend fun saveUserName(userName: String)
    suspend fun saveUserEmail(userEmail: String)
    suspend fun saveUserPhoneNumber(userPhoneNumber: String)
    suspend fun saveAccountNumber(accountNumber: String)
    suspend fun saveAccountPin(accountPin: String)
    suspend fun saveDateOfBirth(dateOfBirth: String)
    suspend fun saveKtpNumber(ktpNumber: String)
    suspend fun saveKtpPhoto(ktpPhoto: String)
    suspend fun deleteUserData()
}