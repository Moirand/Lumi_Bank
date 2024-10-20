package com.example.data.datasource.remote.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @field:SerializedName("data")
    val data: LoginData,

    @field:SerializedName("success")
    val success: Boolean,

    @field:SerializedName("message")
    val message: String
)

data class LoginData(
    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("email")
    val email: String? = null,

    @field:SerializedName("jwt_token")
    val jwtToken: String? = null,

    @field:SerializedName("refresh_token")
    val refreshToken: String? = null
)