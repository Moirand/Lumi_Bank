package com.example.data

import com.example.core.model.response.LoginDataCore
import com.example.core.model.response.LoginResponseCore
import com.example.core.model.response.UserDataCore
import com.example.core.model.response.UserGetResponseCore
import com.example.data.datasource.remote.response.LoginData
import com.example.data.datasource.remote.response.LoginResponse
import com.example.data.datasource.remote.response.UserData
import com.example.data.datasource.remote.response.UserGetResponse

fun LoginResponse.toCore(): LoginResponseCore =
    LoginResponseCore(
        data = data.toCore(),
        success = success,
        message = message
    )

private fun LoginData.toCore(): LoginDataCore =
    LoginDataCore(
        email = email ?: "",
        name = name ?: "",
        jwtToken = jwtToken ?: "",
        refreshToken = refreshToken ?: ""
    )

fun UserGetResponse.toCore(): UserGetResponseCore =
    UserGetResponseCore(
        data = data.toCore(),
        success = success,
        message = message
    )

private fun UserData.toCore(): UserDataCore =
    UserDataCore(
        accountNumber = accountNumber ?: "",
        noHp = noHp ?: "",
        dateOfBirth = dateOfBirth ?: "",
        accountPin = accountPin ?: "",
        noKtp = noKtp ?: "",
        name = name ?: "",
        ektpPhoto = ektpPhoto ?: "",
        email = email ?: ""
    )