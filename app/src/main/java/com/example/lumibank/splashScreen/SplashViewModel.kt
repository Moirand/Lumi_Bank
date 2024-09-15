package com.example.lumibank.splashScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.usecase.TokenGetUseCase
import kotlinx.coroutines.launch

class SplashViewModel(
    private val tokenGetUseCase: TokenGetUseCase,
) : ViewModel() {
    private val _token = MutableLiveData<String?>()
    val token: LiveData<String?> = _token

    init {
        viewModelScope.launch {
            tokenGetUseCase.getToken().collect { tokenValue ->
                _token.value = tokenValue
            }
        }
    }
}