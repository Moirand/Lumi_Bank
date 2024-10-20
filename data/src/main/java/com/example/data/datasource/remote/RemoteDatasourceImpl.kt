package com.example.data.datasource.remote

import com.example.core.Resource
import com.example.core.datasource.RemoteDatasource
import com.example.core.model.request.AccountSaveRequest
import com.example.core.model.request.LoginRequest
import com.example.core.model.request.TransferRequest
import com.example.core.model.response.AccountSaveResponseCore
import com.example.core.model.response.AccountsResponseCore
import com.example.core.model.response.BalanceGetResponseCore
import com.example.core.model.response.LoginResponseCore
import com.example.core.model.response.MutationGetResponseCore
import com.example.core.model.response.MutationsGetResponseCore
import com.example.core.model.response.SavedAccountsGetResponseCore
import com.example.core.model.response.TransferResponseCore
import com.example.core.model.response.UserGetResponseCore
import com.example.data.datasource.remote.network.ApiService
import com.example.data.datasource.remote.response.LoginErrorResponse
import com.example.data.toCore
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class RemoteDatasourceImpl(
    private val apiService: ApiService
) : RemoteDatasource {
    override suspend fun login(loginRequest: LoginRequest): Flow<Resource<LoginResponseCore>> =
        flow {
            emit(Resource.Loading())
//            val response = apiService.login(loginRequest)
            val response = DummyResponse.login(loginRequest)
            emit(Resource.Success(response.toCore()))
        }.catch { e ->
            if (e is HttpException) {
                val errorResponse = e.response()?.errorBody()?.string()
                val json = Gson().fromJson(errorResponse, LoginErrorResponse::class.java)
                emit(Resource.Error(json.errors))
            } else {
                emit(Resource.Error(e.message ?: "An unknown error occurred"))
            }
        }

    override suspend fun getUserData(token: String): Flow<Resource<UserGetResponseCore>> =
        flow {
            emit(Resource.Loading())
//            val response = apiService.getUserData("Bearer $token").toCore()
            val response = DummyResponse.getUserData("Bearer $token").toCore()
            emit(Resource.Success(response))
        }.catch { e ->
            emit(Resource.Error(e.message ?: "An unknown error occurred"))
        }

    override suspend fun getBalance(
        token: String,
        accountNumber: String
    ): Flow<Resource<BalanceGetResponseCore>> =
        flow {
            emit(Resource.Loading())
//            val response = apiService.getBalance("Bearer $token", accountNumber).toCore()
            val response = DummyResponse.getBalance("Bearer $token", accountNumber).toCore()
            emit(Resource.Success(response))
        }.catch { e ->
            emit(Resource.Error(e.message ?: "An unknown error occurred"))
        }

    override suspend fun getAllMutations(
        token: String,
        accountNumber: String
    ): Flow<Resource<MutationsGetResponseCore>> =
        flow {
            emit(Resource.Loading())
//            val response = apiService.getAllMutations("Bearer $token", accountNumber).toCore()
            val response = DummyResponse.getAllMutations("Bearer $token", accountNumber).toCore()
            emit(Resource.Success(response))
        }.catch { e ->
            emit(Resource.Error(e.message ?: "An unknown error occurred"))
        }

    override suspend fun getMutationsByDate(
        token: String,
        accountNumber: String,
        startDate: String,
        endDate: String,
        type: String
    ): Flow<Resource<MutationsGetResponseCore>> =
        flow {
            emit(Resource.Loading())
//            val response = apiService.getMutationsByDate(
//                "Bearer $token",
//                accountNumber,
//                startDate,
//                endDate,
//                type
//            ).toCore()
            val response = DummyResponse.getMutationsByDate(
                "Bearer $token",
                accountNumber,
                startDate,
                endDate,
                type
            ).toCore()
            emit(Resource.Success(response))
        }.catch { e ->
            emit(Resource.Error(e.message ?: "An unknown error occurred"))
        }

    override suspend fun getMutationById(
        token: String,
        id: String
    ): Flow<Resource<MutationGetResponseCore>> =
        flow {
            emit(Resource.Loading())
//            val response = apiService.getMutationById("Bearer $token", id).toCore()
            val response = DummyResponse.getMutationById("Bearer $token", id).toCore()
            emit(Resource.Success(response))
        }.catch { e ->
            emit(Resource.Error(e.message ?: "An unknown error occurred"))
        }

    override suspend fun getAccounts(token: String): Flow<Resource<List<AccountsResponseCore>>> =
        flow {
            emit(Resource.Loading())
//            val response = apiService.getAccounts("Bearer $token").map { it.toCore() }
            val response = DummyResponse.getAccounts("Bearer $token").map { it.toCore() }
            emit(Resource.Success(response))
        }.catch { e ->
            emit(Resource.Error(e.message ?: "An unknown error occurred"))
        }

    override suspend fun getSavedAccounts(token: String): Flow<Resource<SavedAccountsGetResponseCore>> =
        flow {
            emit(Resource.Loading())
//            val response = apiService.getSavedAccounts("Bearer $token").toCore()
            val response = DummyResponse.getSavedAccounts("Bearer $token").toCore()
            emit(Resource.Success(response))
        }.catch { e ->
            emit(Resource.Error(e.message ?: "An unknown error occurred"))
        }

    override suspend fun saveAccount(
        token: String,
        accountSaveRequest: AccountSaveRequest
    ): Flow<Resource<AccountSaveResponseCore>> =
        flow {
            emit(Resource.Loading())
//            val response = apiService.getSavedAccounts("Bearer $token").toCore()
            val response = DummyResponse.saveAccount("Bearer $token", accountSaveRequest).toCore()
            emit(Resource.Success(response))
        }.catch { e ->
            emit(Resource.Error(e.message ?: "An unknown error occurred"))
        }

    override suspend fun transfer(
        token: String,
        transferRequest: TransferRequest
    ): Flow<Resource<TransferResponseCore>> =
        flow {
            emit(Resource.Loading())
//            val response = apiService.transfer("Bearer $token", transferRequest).toCore()
            val response = DummyResponse.transfer("Bearer $token", transferRequest).toCore()
            emit(Resource.Success(response))
        }.catch { e ->
            emit(Resource.Error(e.message ?: "An unknown error occurred"))
        }
}