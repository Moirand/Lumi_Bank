package com.example.core.model.response

data class LoginResponseCore(
    val data: LoginDataCore,
    val success: Boolean,
    val message: String
)

data class LoginDataCore(
    val email: String,
    val name: String,
    val jwtToken: String,
    val refreshToken: String
)