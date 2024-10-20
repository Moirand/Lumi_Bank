package com.example.data.datasource.remote.response

import com.google.gson.annotations.SerializedName

data class AccountSaveResponse(
    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("success")
    val success: Boolean
)