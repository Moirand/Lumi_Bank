package com.example.core.model.response

data class UserGetResponseCore(
    val data: UserDataCore,
    val success: Boolean,
    val message: String
)

data class UserDataCore(
    val accountNumber: String,
    val noHp: String,
    val dateOfBirth: String,
    val accountPin: String,
    val noKtp: String,
    val name: String,
    val ektpPhoto: String,
    val email: String
)