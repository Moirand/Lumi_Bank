package com.example.data.datasource.remote.network

import com.example.core.model.request.LoginRequest
import com.example.data.datasource.remote.response.LoginResponse
import com.example.data.datasource.remote.response.UserGetResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @POST("auth/login")
    suspend fun login(
        @Body loginRequest: LoginRequest,
    ): LoginResponse

    @GET("user/me")
    suspend fun getUserData(
        @Header("Authorization") token: String
    ): UserGetResponse
}