package com.example.data.repository

import com.example.core.Resource
import com.example.core.datasource.RemoteDatasource
import com.example.core.model.request.AccountSaveRequest
import com.example.core.model.response.AccountSaveResponseCore
import com.example.core.model.response.AccountsResponseCore
import com.example.core.model.response.SavedAccountsGetResponseCore
import com.example.core.repository.AccountRepository
import kotlinx.coroutines.flow.Flow

class AccountRepositoryImpl(
    private val remoteDatasource: RemoteDatasource
) : AccountRepository {
    override suspend fun getAccounts(token: String): Flow<Resource<List<AccountsResponseCore>>> =
        remoteDatasource.getAccounts(token)

    override suspend fun getSavedAccounts(token: String): Flow<Resource<SavedAccountsGetResponseCore>> =
        remoteDatasource.getSavedAccounts(token)

    override suspend fun saveAccount(token: String, accountSaveRequest: AccountSaveRequest): Flow<Resource<AccountSaveResponseCore>> =
        remoteDatasource.saveAccount(token, accountSaveRequest)
}