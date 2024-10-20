package com.example.data.datasource.remote.response

import com.google.gson.annotations.SerializedName

data class PhoneNumberCheckResponse(
    @field:SerializedName("data")
    val data: String,

    @field:SerializedName("success")
    val success: Boolean
)