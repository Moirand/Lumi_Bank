package com.example.feature_transfer.viewModel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.Resource
import com.example.core.model.request.AccountSaveRequest
import com.example.core.model.request.TransferRequest
import com.example.core.model.response.AccountSaveResponseCore
import com.example.core.model.response.AccountsResponseCore
import com.example.core.model.response.BalanceGetResponseCore
import com.example.core.model.response.MutationGetResponseCore
import com.example.core.model.response.SavedAccountsGetResponseCore
import com.example.core.model.response.TransferResponseCore
import com.example.core.usecase.BalanceGetUseCase
import com.example.core.usecase.LocalUserGetUseCase
import com.example.core.usecase.MutationGetUseCase
import com.example.core.usecase.AccountGetUseCase
import com.example.core.usecase.AccountSetUseCase
import com.example.core.usecase.TokenGetUseCase
import com.example.core.usecase.TransferUseCase
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.ZoneOffset

class TransferViewModel(
    private val transferUseCase: TransferUseCase,
    private val mutationUseCase: MutationGetUseCase,
    private val balanceGetUseCase: BalanceGetUseCase,
    private val accountGetUseCase: AccountGetUseCase,
    private val accountSetUseCase: AccountSetUseCase,
    private val tokenGetUseCase: TokenGetUseCase,
    private val localUserGetUseCase: LocalUserGetUseCase
) : ViewModel() {
    private val _mutationData = MutableLiveData<Resource<MutationGetResponseCore>>()
    val mutationData: LiveData<Resource<MutationGetResponseCore>> = _mutationData

    private val _balanceData = MutableLiveData<Resource<BalanceGetResponseCore>>()
    val balanceData: LiveData<Resource<BalanceGetResponseCore>> = _balanceData

    private val _transferResult = MutableLiveData<Resource<TransferResponseCore>>()
    val transferResult: LiveData<Resource<TransferResponseCore>> = _transferResult

    private val _savedAccountsData = MutableLiveData<Resource<SavedAccountsGetResponseCore>>()
    val savedAccountsData: LiveData<Resource<SavedAccountsGetResponseCore>> = _savedAccountsData

    private val _accountSaveResponse = MutableLiveData<Resource<AccountSaveResponseCore>>()
    val accountSaveResponse: LiveData<Resource<AccountSaveResponseCore>> = _accountSaveResponse

    private val _checkedAccount = MutableLiveData<Resource<List<AccountsResponseCore>>>()
    val checkedAccount: LiveData<Resource<List<AccountsResponseCore>>> = _checkedAccount

    @RequiresApi(Build.VERSION_CODES.O)
    fun transfer(
        destinationNo: String,
        amount: Int,
        destinationBank: String,
        description: String,
        pin: String
    ) = viewModelScope.launch {
        tokenGetUseCase.getToken()
            .zip(localUserGetUseCase.loadAccountNumber()) { token, accountNumber ->
                val currentDateTime = LocalDateTime.now(ZoneOffset.UTC)
                val dateTimePlus7Hours = currentDateTime.plusHours(14)

                val transferRequest = TransferRequest(
                    accountFrom = accountNumber!!,
                    accountTo = destinationNo,
                    amount = amount,
                    description = description,
                    pin = pin,
                    datetime = dateTimePlus7Hours.toString(),
                    destinationBank = destinationBank
                )
                transferUseCase.transfer(token!!, transferRequest).collect { response ->
                    _transferResult.value = response
                }
            }
    }

    fun getSavedAccounts() = viewModelScope.launch {
        tokenGetUseCase.getToken().collect { token ->
            accountGetUseCase.getSavedAccounts(token!!).collect { response ->
                _savedAccountsData.value = response
            }
        }
    }

    fun getBalance() = viewModelScope.launch {
        tokenGetUseCase.getToken()
            .zip(localUserGetUseCase.loadAccountNumber()) { token, accountNumber ->
                balanceGetUseCase.getBalance(token!!, accountNumber!!).collect { response ->
                    _balanceData.value = response
                }
            }
    }

    fun getMutationById(id: String) = viewModelScope.launch {
        tokenGetUseCase.getToken().collect { token ->
            mutationUseCase.getMutationById(token!!, id).collect {
                _mutationData.value = it
            }
        }
    }

    fun getAccountByAccountNumber(accountNumber: String, bankName: String) = viewModelScope.launch {
        tokenGetUseCase.getToken().collect { token ->
            accountGetUseCase.getAccounts(token!!).collect {
                _checkedAccount.value = it
            }
        }
    }

    fun saveAccount(accountNumber: String, bankName: String) = viewModelScope.launch {
        tokenGetUseCase.getToken().collect { token ->
            val accountSaveRequest = AccountSaveRequest(
                accountNumber = accountNumber,
                destinationBank = bankName
            )
            accountSetUseCase.saveAccount(token!!, accountSaveRequest).collect {
                _accountSaveResponse.value = it
            }
        }
    }
}