package com.example.core.model.response

data class MutationGetResponseCore(
    val data: List<MutationDataCore>?,
    val success: Boolean,
    val message: String
)

data class MutationDataCore(
    val usernameFrom: String? = null,
    val amount: Double? = null,
    val datetime: String? = null,
    val accountTo: String? = null,
    val balance: Double? = null,
    val usernameTo: String? = null,
    val description: String? = null,
    val id: String? = null,
    val accountFrom: String? = null,
    val type: String? = null,
    val status: String? = null
)