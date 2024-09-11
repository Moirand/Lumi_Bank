package com.example.data.datasource.remote.response

import com.google.gson.annotations.SerializedName

data class LoginErrorResponse(
    @field:SerializedName("success")
    val success: Boolean,

    @field:SerializedName("errors")
    val errors: String
)