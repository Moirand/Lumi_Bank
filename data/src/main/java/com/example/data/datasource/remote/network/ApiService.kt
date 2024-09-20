package com.example.data.datasource.remote.network

import com.example.core.model.request.LoginRequest
import com.example.data.datasource.remote.response.BalanceGetResponse
import com.example.data.datasource.remote.response.LoginResponse
import com.example.data.datasource.remote.response.MutationGetResponse
import com.example.data.datasource.remote.response.UserGetResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @POST("auth/login")
    suspend fun login(
        @Body loginRequest: LoginRequest,
    ): LoginResponse

    @GET("user/me")
    suspend fun getUserData(
        @Header("Authorization") token: String
    ): UserGetResponse

    @GET("balance/get")
    suspend fun getBalance(
        @Header("Authorization") token: String,
        @Query("accountNumber") accountNumber: String
    ): BalanceGetResponse

    @GET("transaction/mutations")
    suspend fun getAllMutations(
        @Header("Authorization") token: String,
        @Query("accountNumber") accountNumber: String
    ): MutationGetResponse

    @GET("transaction/mutation/date")
    suspend fun getMutationsByDate(
        @Header("Authorization") token: String,
        @Query("accountNumber") accountNumber: String,
        @Query("startDate") startDate: String,
        @Query("endDate") endDate: String,
        @Query("type") type: String,
    ): MutationGetResponse
}