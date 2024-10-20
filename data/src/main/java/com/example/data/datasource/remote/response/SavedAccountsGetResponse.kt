package com.example.data.datasource.remote.response

import com.google.gson.annotations.SerializedName

data class SavedAccountsGetResponse(
    @field:SerializedName("data")
    val data: List<SavedAccountGetData>,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("success")
    val success: Boolean
)

data class SavedAccountGetData(
    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("accountNumber")
    val accountNumber: String? = null,

    @field:SerializedName("destinationBank")
    val destinationBank: String? = null
)