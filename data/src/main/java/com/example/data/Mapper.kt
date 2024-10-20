package com.example.data

import com.example.core.model.response.AccountSaveResponseCore
import com.example.core.model.response.AccountsResponseCore
import com.example.core.model.response.BalanceGetResponseCore
import com.example.core.model.response.EmailCheckResponseCore
import com.example.core.model.response.ForgotPasswordResponseCore
import com.example.core.model.response.KtpNumberCheckResponseCore
import com.example.core.model.response.LoginDataCore
import com.example.core.model.response.LoginResponseCore
import com.example.core.model.response.MutationDataCore
import com.example.core.model.response.MutationGetResponseCore
import com.example.core.model.response.MutationsGetResponseCore
import com.example.core.model.response.NotificationGetResponseCore
import com.example.core.model.response.OtpResponseCore
import com.example.core.model.response.PhoneNumberCheckResponseCore
import com.example.core.model.response.RegisterDataCore
import com.example.core.model.response.RegisterResponseCore
import com.example.core.model.response.SavedAccountGetDataCore
import com.example.core.model.response.SavedAccountsGetResponseCore
import com.example.core.model.response.TransferDataCore
import com.example.core.model.response.TransferResponseCore
import com.example.core.model.response.UserDataCore
import com.example.core.model.response.UserGetResponseCore
import com.example.data.datasource.remote.response.AccountSaveResponse
import com.example.data.datasource.remote.response.AccountsResponse
import com.example.data.datasource.remote.response.BalanceGetResponse
import com.example.data.datasource.remote.response.EmailCheckResponse
import com.example.data.datasource.remote.response.ForgotPasswordResponse
import com.example.data.datasource.remote.response.KtpNumberCheckResponse
import com.example.data.datasource.remote.response.LoginData
import com.example.data.datasource.remote.response.LoginResponse
import com.example.data.datasource.remote.response.MutationData
import com.example.data.datasource.remote.response.MutationGetResponse
import com.example.data.datasource.remote.response.MutationsGetResponse
import com.example.data.datasource.remote.response.NotificationGetResponse
import com.example.data.datasource.remote.response.OtpResponse
import com.example.data.datasource.remote.response.PhoneNumberCheckResponse
import com.example.data.datasource.remote.response.RegisterData
import com.example.data.datasource.remote.response.RegisterResponse
import com.example.data.datasource.remote.response.SavedAccountGetData
import com.example.data.datasource.remote.response.SavedAccountsGetResponse
import com.example.data.datasource.remote.response.TransferData
import com.example.data.datasource.remote.response.TransferResponse
import com.example.data.datasource.remote.response.UserData
import com.example.data.datasource.remote.response.UserGetResponse

fun AccountSaveResponse.toCore(): AccountSaveResponseCore =
    AccountSaveResponseCore(
        message = message ?: "",
        success = success
    )

fun AccountsResponse.toCore(): AccountsResponseCore =
    AccountsResponseCore(
        id = id ?: "",
        userId = userId ?: "",
        userName = userName ?: "",
        accountNumber = accountNumber ?: "",
        bankName = bankName ?: ""
    )

fun BalanceGetResponse.toCore(): BalanceGetResponseCore =
    BalanceGetResponseCore(
        data = data,
        success = success,
        message = message
    )

fun EmailCheckResponse.toCore(): EmailCheckResponseCore =
    EmailCheckResponseCore(
        data = data,
        success = success
    )

fun ForgotPasswordResponse.toCore(): ForgotPasswordResponseCore =
    ForgotPasswordResponseCore(
        status = status,
        message = message
    )

fun KtpNumberCheckResponse.toCore(): KtpNumberCheckResponseCore =
    KtpNumberCheckResponseCore(
        data = data,
        success = success
    )

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

fun MutationsGetResponse.toCore(): MutationsGetResponseCore =
    MutationsGetResponseCore(
        data = data?.map { it.toCore() },
        success = success,
        message = message
    )

fun MutationGetResponse.toCore(): MutationGetResponseCore =
    MutationGetResponseCore(
        data = data?.toCore(),
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

fun NotificationGetResponse.toCore(): NotificationGetResponseCore =
    NotificationGetResponseCore(
        id = id ?: "",
        title = title ?: "",
        body = body ?: "",
        sentAt = sentAt ?: "",
        read = read
    )

fun OtpResponse.toCore(): OtpResponseCore =
    OtpResponseCore(
        message = "",
        success = true
    )

fun PhoneNumberCheckResponse.toCore(): PhoneNumberCheckResponseCore =
    PhoneNumberCheckResponseCore(
        data = data,
        success = success
    )

fun RegisterResponse.toCore(): RegisterResponseCore =
    RegisterResponseCore(
        data = data.toCore(),
        success = success,
        message = message
    )

private fun RegisterData.toCore(): RegisterDataCore =
    RegisterDataCore(
        email = email ?: "",
        name = name ?: "",
        accountNumber = accountNumber ?: "",
        accountPin = accountPin ?: "",
        noKtp = noKtp ?: "",
        noHp = noHp ?: "",
        dateOfBirth = dateOfBirth ?: "",
        ektpPhoto = ektpPhoto ?: ""
    )

fun SavedAccountsGetResponse.toCore(): SavedAccountsGetResponseCore =
    SavedAccountsGetResponseCore(
        data = data.map { it.toCore() },
        success = success,
        message = message
    )

private fun SavedAccountGetData.toCore(): SavedAccountGetDataCore =
    SavedAccountGetDataCore(
        accountNumber = accountNumber ?: "",
        name = name ?: "",
        id = id ?: "",
        destinationBank = destinationBank ?: ""
    )

fun TransferResponse.toCore(): TransferResponseCore =
    TransferResponseCore(
        data = data.toCore(),
        success = success,
        message = message
    )

private fun TransferData.toCore(): TransferDataCore =
    TransferDataCore(
        amount = amount ?: 0,
        destinationBank = destinationBank ?: "",
        description = description ?: "",
        type = type ?: "",
        accountFrom = accountFrom ?: "",
        createdAt = createdAt ?: "",
        accountTo = accountTo ?: "",
        datetime = datetime ?: "",
        balance = balance ?: 0.0,
        referenceNumber = referenceNumber ?: "",
        nameAccountFrom = nameAccountFrom ?: "",
        id = id ?: "",
        nameAccountTo = nameAccountTo ?: "",
        status = status ?: ""
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