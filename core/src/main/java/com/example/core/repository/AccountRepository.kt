package com.example.core.repository

import com.example.core.Resource
import com.example.core.model.request.AccountSaveRequest
import com.example.core.model.response.AccountSaveResponseCore
import com.example.core.model.response.AccountsResponseCore
import com.example.core.model.response.SavedAccountsGetResponseCore
import kotlinx.coroutines.flow.Flow

interface AccountRepository {
    suspend fun getAccounts(token: String): Flow<Resource<List<AccountsResponseCore>>>
    suspend fun getSavedAccounts(token: String): Flow<Resource<SavedAccountsGetResponseCore>>
    suspend fun saveAccount(token: String, accountSaveRequest: AccountSaveRequest): Flow<Resource<AccountSaveResponseCore>>
}