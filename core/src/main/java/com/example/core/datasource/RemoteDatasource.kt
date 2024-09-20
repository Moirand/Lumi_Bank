package com.example.core.datasource

import com.example.core.Resource
import com.example.core.model.request.LoginRequest
import com.example.core.model.response.BalanceGetResponseCore
import com.example.core.model.response.LoginResponseCore
import com.example.core.model.response.MutationGetResponseCore
import com.example.core.model.response.UserGetResponseCore
import kotlinx.coroutines.flow.Flow

interface RemoteDatasource {
    suspend fun login(loginRequest: LoginRequest): Flow<Resource<LoginResponseCore>>
    suspend fun getUserData(token: String): Flow<Resource<UserGetResponseCore>>
    suspend fun getBalance(token: String, accountNumber: String): Flow<Resource<BalanceGetResponseCore>>
    suspend fun getAllMutations(token: String, accountNumber: String): Flow<Resource<MutationGetResponseCore>>
    suspend fun getMutationsByDate(token: String, accountNumber: String, startDate: String, endDate: String, type: String): Flow<Resource<MutationGetResponseCore>>
}