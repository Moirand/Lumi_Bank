package com.example.core.usecase

import com.example.core.Resource
import com.example.core.model.response.AccountsResponseCore
import com.example.core.model.response.SavedAccountsGetResponseCore
import kotlinx.coroutines.flow.Flow

interface AccountGetUseCase {
    suspend fun getAccounts(token: String): Flow<Resource<List<AccountsResponseCore>>>
    suspend fun getSavedAccounts(token: String): Flow<Resource<SavedAccountsGetResponseCore>>
}