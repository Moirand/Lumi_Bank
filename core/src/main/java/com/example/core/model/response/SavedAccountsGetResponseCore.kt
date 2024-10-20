package com.example.core.model.response

data class SavedAccountsGetResponseCore(
    val data: List<SavedAccountGetDataCore?>,
    val success: Boolean,
    val message: String
)

data class SavedAccountGetDataCore(
    val name: String? = null,
    val id: String? = null,
    val accountNumber: String? = null,
    val destinationBank: String? = null
)