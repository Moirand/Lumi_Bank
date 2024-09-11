package com.example.core.datasource

import kotlinx.coroutines.flow.Flow

interface LocalDatasource {
    // TOKEN
    suspend fun saveToken(token: String)
    suspend fun loadToken(): Flow<String?>
    suspend fun deleteToken()
    suspend fun isTokenExpired(): Flow<Boolean>

    // USER DATA
    suspend fun saveUserName(userName: String)
    suspend fun loadUserName(): Flow<String?>
    suspend fun saveUserEmail(userEmail: String)
    suspend fun loadUserEmail(): Flow<String?>
    suspend fun saveUserPhoneNumber(userPhoneNumber: String)
    suspend fun loadUserPhoneNumber(): Flow<String?>
    suspend fun saveAccountNumber(accountNumber: String)
    suspend fun loadAccountNumber(): Flow<String?>
    suspend fun saveAccountPin(accountPin: String)
    suspend fun loadAccountPin(): Flow<String?>
    suspend fun saveDateOfBirth(dateOfBirth: String)
    suspend fun loadDateOfBirth(): Flow<String?>
    suspend fun saveKtpNumber(ktpNumber: String)
    suspend fun loadKtpNumber(): Flow<String?>
    suspend fun saveKtpPhoto(ktpPhoto: String)
    suspend fun loadKtpPhoto(): Flow<String?>
    suspend fun deleteUserData()
}