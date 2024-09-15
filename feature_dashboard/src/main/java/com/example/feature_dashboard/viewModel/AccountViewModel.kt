package com.example.feature_dashboard.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.model.response.UserDataCore
import com.example.core.usecase.LocalUserGetUseCase
import com.example.core.usecase.LocalUserSetUseCase
import com.example.core.usecase.TokenSetUseCase
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class AccountViewModel(
    private val tokenSetUseCase: TokenSetUseCase,
    private val localUserSetUseCase: LocalUserSetUseCase,
    private val localUserGetUseCase: LocalUserGetUseCase
) : ViewModel() {
    private val _localUserData = MutableLiveData<UserDataCore>()
    val localUserData: LiveData<UserDataCore> = _localUserData

    init {
        getLocalUserData()
    }

    private fun getLocalUserData() = viewModelScope.launch {
        _localUserData.value = with(localUserGetUseCase) {
            UserDataCore(
                name = loadUserName().firstOrNull() ?: "",
                email = loadUserEmail().firstOrNull() ?: "",
                noHp = loadUserPhoneNumber().firstOrNull() ?: "",
                accountNumber = loadAccountNumber().firstOrNull() ?: "",
                accountPin = loadAccountPin().firstOrNull() ?: "",
                dateOfBirth = loadDateOfBirth().firstOrNull() ?: "",
                noKtp = loadKtpNumber().firstOrNull() ?: "",
                ektpPhoto = loadKtpPhoto().firstOrNull() ?: ""
            )
        }
    }

    fun deleteToken(): Deferred<Unit> = viewModelScope.async {
        tokenSetUseCase.deleteToken()
    }

    fun deleteUserData(): Deferred<Unit> = viewModelScope.async {
        localUserSetUseCase.deleteUserData()
    }
}