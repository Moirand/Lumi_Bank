package com.example.core.model.request

data class AccountSaveRequest(
    val accountNumber: String,
    val destinationBank: String
)