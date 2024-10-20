package com.example.core.model.response

data class RegisterResponseCore(
    val data: RegisterDataCore,
    val message: String,
    val success: Boolean
)

data class RegisterDataCore(
    val name: String? = null,
    val email: String? = null,
    val noHp: String? = null,
    val dateOfBirth: String? = null,
    val accountPin: String? = null,
    val accountNumber: String? = null,
    val noKtp: String? = null,
    val ektpPhoto: String? = null
)