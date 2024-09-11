package com.example.data.datasource.remote

import com.example.core.Resource
import com.example.core.datasource.RemoteDatasource
import com.example.core.model.request.LoginRequest
import com.example.core.model.response.LoginResponseCore
import com.example.core.model.response.UserGetResponseCore
import com.example.data.datasource.remote.network.ApiService
import com.example.data.datasource.remote.response.LoginErrorResponse
import com.example.data.toCore
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class RemoteDatasourceImpl(
    private val apiService: ApiService
) : RemoteDatasource {
    override suspend fun login(loginRequest: LoginRequest): Flow<Resource<LoginResponseCore>> = flow {
        try {
            emit(Resource.Loading())
            val response = apiService.login(loginRequest)
            emit(Resource.Success(response.toCore()))
        } catch (e: HttpException) {
            val errorResponse = e.response()?.errorBody()?.string()
            val json = Gson().fromJson(errorResponse, LoginErrorResponse::class.java)
            emit(Resource.Error(json.errors))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }

    override suspend fun getUserData(token: String): Flow<Resource<UserGetResponseCore>> = flow {
        try {
            emit(Resource.Loading())
            val response = apiService.getUserData(token).toCore()
            emit(Resource.Success(response))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }
}