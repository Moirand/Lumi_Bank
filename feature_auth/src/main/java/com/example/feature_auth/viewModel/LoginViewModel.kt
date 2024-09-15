package com.example.feature_auth.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.Resource
import com.example.core.model.request.LoginRequest
import com.example.core.model.response.LoginResponseCore
import com.example.core.model.response.UserDataCore
import com.example.core.model.response.UserGetResponseCore
import com.example.core.usecase.LocalUserSetUseCase
import com.example.core.usecase.LoginUseCase
import com.example.core.usecase.RemoteUserGetUseCase
import com.example.core.usecase.TokenSetUseCase
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val tokenSetUseCase: TokenSetUseCase,
    private val localUserSetUseCase: LocalUserSetUseCase,
    private val remoteUserGetUseCase: RemoteUserGetUseCase
) : ViewModel() {
    private val _loginResult = MutableLiveData<Resource<LoginResponseCore>>()
    val loginResult: LiveData<Resource<LoginResponseCore>> = _loginResult

    private val _remoteUserData = MutableLiveData<Resource<UserGetResponseCore>>()
    val remoteUserData: LiveData<Resource<UserGetResponseCore>> = _remoteUserData

    fun login(email: String, password: String) = viewModelScope.launch {
        loginUseCase.login(LoginRequest(email, password))
            .collect { _loginResult.value = it }
    }

    fun getRemoteUserData(token: String) = viewModelScope.launch {
        remoteUserGetUseCase.getUserData(token)
            .collect { _remoteUserData.value = it }
    }

    fun saveToken(token: String): Deferred<Unit> = viewModelScope.async {
        tokenSetUseCase.saveToken(token)
    }

    fun saveUserData(userData: UserDataCore?): Deferred<Unit> = viewModelScope.async {
        localUserSetUseCase.apply {
            saveUserName(userData?.name ?: "")
            saveUserEmail(userData?.email ?: "")
            saveUserPhoneNumber(userData?.noHp ?: "")
            saveAccountNumber(userData?.accountNumber ?: "")
            saveAccountPin(userData?.accountPin ?: "")
            saveDateOfBirth(userData?.dateOfBirth ?: "")
            saveKtpNumber(userData?.noKtp ?: "")
            saveKtpPhoto(userData?.ektpPhoto ?: "")
        }
    }
}