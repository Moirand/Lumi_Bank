package com.example.feature_dashboard.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.Resource
import com.example.core.model.response.BalanceGetResponseCore
import com.example.core.model.response.UserDataCore
import com.example.core.usecase.BalanceGetUseCase
import com.example.core.usecase.LocalUserGetUseCase
import com.example.core.usecase.TokenGetUseCase
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class HomeViewModel(
    private val balanceGetUseCase: BalanceGetUseCase,
    private val tokenGetUseCase: TokenGetUseCase,
    private val localUserGetUseCase: LocalUserGetUseCase
) : ViewModel() {
    private var _token: String? = null

    private val _localUserData = MutableLiveData<UserDataCore>()
    val localUserData: LiveData<UserDataCore> = _localUserData

    private val _balanceGetResult = MutableLiveData<Resource<BalanceGetResponseCore>>()
    val balanceGetResult: LiveData<Resource<BalanceGetResponseCore>> = _balanceGetResult

    init {
        getToken()
        getLocalUserData()
    }

    private fun getToken() = viewModelScope.launch {
        tokenGetUseCase.getToken().collect { tokenValue ->
            _token = tokenValue
        }
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

    fun getBalance(accountNumber: String) = viewModelScope.launch {
        if (_token != null) {
            balanceGetUseCase.getBalance(_token!!, accountNumber)
                .collect { _balanceGetResult.value = it }
        }
    }
}