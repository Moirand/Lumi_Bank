package com.example.core.datasource

import com.example.core.Resource
import com.example.core.model.request.AccountSaveRequest
import com.example.core.model.request.LoginRequest
import com.example.core.model.request.TransferRequest
import com.example.core.model.response.AccountSaveResponseCore
import com.example.core.model.response.AccountsResponseCore
import com.example.core.model.response.BalanceGetResponseCore
import com.example.core.model.response.LoginResponseCore
import com.example.core.model.response.MutationGetResponseCore
import com.example.core.model.response.MutationsGetResponseCore
import com.example.core.model.response.SavedAccountsGetResponseCore
import com.example.core.model.response.TransferResponseCore
import com.example.core.model.response.UserGetResponseCore
import kotlinx.coroutines.flow.Flow

interface RemoteDatasource {
    suspend fun login(loginRequest: LoginRequest): Flow<Resource<LoginResponseCore>>
    suspend fun getUserData(token: String): Flow<Resource<UserGetResponseCore>>
    suspend fun getBalance(token: String, accountNumber: String): Flow<Resource<BalanceGetResponseCore>>
    suspend fun getAllMutations(token: String, accountNumber: String): Flow<Resource<MutationsGetResponseCore>>
    suspend fun getMutationsByDate(token: String, accountNumber: String, startDate: String, endDate: String, type: String): Flow<Resource<MutationsGetResponseCore>>
    suspend fun getMutationById(token: String, id: String): Flow<Resource<MutationGetResponseCore>>
    suspend fun getAccounts(token: String): Flow<Resource<List<AccountsResponseCore>>>
    suspend fun getSavedAccounts(token: String): Flow<Resource<SavedAccountsGetResponseCore>>
    suspend fun saveAccount(token: String, accountSaveRequest: AccountSaveRequest): Flow<Resource<AccountSaveResponseCore>>
    suspend fun transfer(token: String, transferRequest: TransferRequest): Flow<Resource<TransferResponseCore>>
}