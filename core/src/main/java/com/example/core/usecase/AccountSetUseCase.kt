package com.example.core.usecase

import com.example.core.Resource
import com.example.core.model.request.AccountSaveRequest
import com.example.core.model.response.AccountSaveResponseCore
import kotlinx.coroutines.flow.Flow

interface AccountSetUseCase {
    suspend fun saveAccount(
        token: String,
        accountSaveRequest: AccountSaveRequest
    ): Flow<Resource<AccountSaveResponseCore>>
}