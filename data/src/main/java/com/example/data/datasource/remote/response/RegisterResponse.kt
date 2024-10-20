package com.example.data.datasource.remote.response

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @field:SerializedName("data")
    val data: RegisterData,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("success")
    val success: Boolean
)

data class RegisterData(
    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("email")
    val email: String? = null,

    @field:SerializedName("no_hp")
    val noHp: String? = null,

    @field:SerializedName("date_of_birth")
    val dateOfBirth: String? = null,

    @field:SerializedName("account_pin")
    val accountPin: String? = null,

    @field:SerializedName("account_number")
    val accountNumber: String? = null,

    @field:SerializedName("no_ktp")
    val noKtp: String? = null,

    @field:SerializedName("ektp_photo")
    val ektpPhoto: String? = null
)