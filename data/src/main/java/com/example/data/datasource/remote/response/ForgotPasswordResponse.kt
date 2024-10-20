package com.example.data.datasource.remote.response

import com.google.gson.annotations.SerializedName

data class ForgotPasswordResponse(
    @field:SerializedName("status")
    val status: Boolean,

    @field:SerializedName("message")
    val message: String
)