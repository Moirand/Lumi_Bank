package com.example.domain.usecase

import com.example.core.Resource
import com.example.core.model.response.AccountsResponseCore
import com.example.core.model.response.SavedAccountsGetResponseCore
import com.example.core.repository.AccountRepository
import com.example.core.usecase.AccountGetUseCase
import kotlinx.coroutines.flow.Flow

class AccountGetUseCaseImpl(
    private val accountRepository: AccountRepository
) : AccountGetUseCase {
    override suspend fun getAccounts(token: String): Flow<Resource<List<AccountsResponseCore>>> =
        accountRepository.getAccounts(token)

    override suspend fun getSavedAccounts(token: String): Flow<Resource<SavedAccountsGetResponseCore>> =
        accountRepository.getSavedAccounts(token)
}