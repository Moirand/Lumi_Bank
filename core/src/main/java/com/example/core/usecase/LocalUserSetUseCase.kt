package com.example.core.usecase

interface LocalUserSetUseCase {
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