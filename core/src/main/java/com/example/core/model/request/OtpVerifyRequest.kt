package com.example.core.model.request

data class OtpVerifyRequest(
    val email: String,
    val otp: String
)