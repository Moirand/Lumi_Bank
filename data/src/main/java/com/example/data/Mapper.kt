package com.example.data

import com.example.core.model.response.BalanceGetResponseCore
import com.example.core.model.response.LoginDataCore
import com.example.core.model.response.LoginResponseCore
import com.example.core.model.response.MutationDataCore
import com.example.core.model.response.MutationGetResponseCore
import com.example.core.model.response.UserDataCore
import com.example.core.model.response.UserGetResponseCore
import com.example.data.datasource.remote.response.BalanceGetResponse
import com.example.data.datasource.remote.response.LoginData
import com.example.data.datasource.remote.response.LoginResponse
import com.example.data.datasource.remote.response.MutationData
import com.example.data.datasource.remote.response.MutationGetResponse
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

fun BalanceGetResponse.toCore(): BalanceGetResponseCore =
    BalanceGetResponseCore(
        data = data,
        success = success,
        message = message
    )

fun MutationGetResponse.toCore(): MutationGetResponseCore =
    MutationGetResponseCore(
        data = data?.map { it.toCore() },
        success = success,
        message = message
    )

private fun MutationData.toCore(): MutationDataCore =
    MutationDataCore(
        usernameFrom = usernameFrom ?: "",
        amount = amount ?: 0.0,
        datetime = datetime ?: "",
        accountTo = accountTo ?: "",
        balance = balance ?: 0.0,
        usernameTo = usernameTo ?: "",
        description = description ?: "",
        id = id ?: "",
        accountFrom = accountFrom ?: "",
        type = type ?: "",
        status = status ?: ""
    )