package com.example.domain.usecase

import com.example.core.Resource
import com.example.core.model.request.AccountSaveRequest
import com.example.core.model.response.AccountSaveResponseCore
import com.example.core.repository.AccountRepository
import com.example.core.usecase.AccountSetUseCase
import kotlinx.coroutines.flow.Flow

class AccountSetUseCaseImpl(
    private val accountRepository: AccountRepository
) : AccountSetUseCase {
    override suspend fun saveAccount(
        token: String,
        accountSaveRequest: AccountSaveRequest
    ): Flow<Resource<AccountSaveResponseCore>> =
        accountRepository.saveAccount(token, accountSaveRequest)
}