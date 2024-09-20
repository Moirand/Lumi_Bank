package com.example.feature_mutasi.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.Resource
import com.example.core.model.response.MutationDataCore
import com.example.core.model.response.MutationGetResponseCore
import com.example.core.usecase.LocalUserGetUseCase
import com.example.core.usecase.MutationGetUseCase
import com.example.core.usecase.TokenGetUseCase
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

class MutationViewmodel(
    private val mutationUseCase: MutationGetUseCase,
    private val tokenGetUseCase: TokenGetUseCase,
    private val localUserGetUseCase: LocalUserGetUseCase
) : ViewModel() {
    var accountNumbers: String? = null

    private val _mutationData = MutableLiveData<Resource<MutationGetResponseCore>>()
    val mutationData: LiveData<Resource<MutationGetResponseCore>> = _mutationData

    fun getMutations(
        startDate: String? = null,
        endDate: String? = null,
        type: String? = "transfer"
    ) = viewModelScope.launch {
        localUserGetUseCase.loadAccountNumber().collect { accountNumber ->
            accountNumbers = accountNumber
            tokenGetUseCase.getToken().collect { tokenValue ->
                if (startDate == null && endDate == null) {
                    mutationUseCase.getAllMutations(tokenValue!!, accountNumber!!)
                } else {
                    mutationUseCase.getMutationsByDate(
                        token = tokenValue!!,
                        accountNumber = accountNumber!!,
                        startDate = startDate!!,
                        endDate = endDate!!,
                        type = type!!
                    )
                }.collect {
                    _mutationData.value = it
                }
            }
        }
    }

    fun filterMutationByType(
        mutationData: List<MutationDataCore>,
        transactionType: String,
        accountNumber: String?
    ): List<MutationDataCore> =
        when (transactionType) {
            "Semua" -> mutationData
            "Uang Masuk" -> mutationData.filter { it.accountFrom != accountNumber }
            "Uang Keluar" -> mutationData.filter { it.accountFrom == accountNumber }
            else -> throw Exception("Jenis transaksi tidak valid")
        }

    fun groupMutationsByDate(mutations: List<MutationDataCore>): Map<String, List<MutationDataCore>> {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        val outputFormat = SimpleDateFormat("d MMMM yyyy", Locale("id", "ID"))

        return mutations.groupBy { mutation ->
            val date = inputFormat.parse(mutation.datetime!!)
            outputFormat.format(date!!)
        }
    }
}