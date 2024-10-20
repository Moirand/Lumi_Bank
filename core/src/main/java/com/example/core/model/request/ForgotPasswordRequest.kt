package com.example.core.model.request

data class ForgotPasswordRequest(
    val email: String,
    val otp: String,
    val newPassword: String,
)