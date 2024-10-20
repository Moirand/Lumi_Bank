package com.example.data.datasource.remote.network

import com.example.core.model.request.AccountSaveRequest
import com.example.core.model.request.EmailCheckRequest
import com.example.core.model.request.ForgotPasswordRequest
import com.example.core.model.request.OtpGetRequest
import com.example.core.model.request.KtpNumberCheckRequest
import com.example.core.model.request.LoginRequest
import com.example.core.model.request.PhoneNumberCheckRequest
import com.example.core.model.request.RegisterRequest
import com.example.core.model.request.TransferRequest
import com.example.core.model.request.OtpVerifyRequest
import com.example.data.datasource.remote.response.AccountSaveResponse
import com.example.data.datasource.remote.response.BalanceGetResponse
import com.example.data.datasource.remote.response.AccountsResponse
import com.example.data.datasource.remote.response.EmailCheckResponse
import com.example.data.datasource.remote.response.ForgotPasswordResponse
import com.example.data.datasource.remote.response.KtpNumberCheckResponse
import com.example.data.datasource.remote.response.LoginResponse
import com.example.data.datasource.remote.response.MutationGetResponse
import com.example.data.datasource.remote.response.MutationsGetResponse
import com.example.data.datasource.remote.response.NotificationGetResponse
import com.example.data.datasource.remote.response.OtpResponse
import com.example.data.datasource.remote.response.PhoneNumberCheckResponse
import com.example.data.datasource.remote.response.RegisterResponse
import com.example.data.datasource.remote.response.SavedAccountsGetResponse
import com.example.data.datasource.remote.response.TransferResponse
import com.example.data.datasource.remote.response.UserGetResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @POST("account-list/save")
    suspend fun saveAccount(
        @Header("Authorization") token: String,
        @Body accountSaveRequest: AccountSaveRequest
    ): AccountSaveResponse

    @GET("accounts")
    suspend fun getAccounts(
        @Header("Authorization") token: String,
    ): List<AccountsResponse>

    @GET("balance/get")
    suspend fun getBalance(
        @Header("Authorization") token: String,
        @Query("accountNumber") accountNumber: String
    ): BalanceGetResponse

    @POST("auth/check-email")
    suspend fun checkEmailAvailability(
        @Body emailCheckRequest: EmailCheckRequest
    ): EmailCheckResponse

    @POST("forget-password/send")
    suspend fun sendForgetPassword(
        @Body sendForgetPassRequest: ForgotPasswordRequest
    ): ForgotPasswordResponse

    @POST("forget-password/validate")
    suspend fun validateForgetPassword(
        @Body validateForgetPass: ForgotPasswordRequest
    ): ForgotPasswordResponse

    @POST("forget-password/change-password")
    suspend fun setNewPassword(
        @Body setNewPassRequest: ForgotPasswordRequest
    ): ForgotPasswordResponse

    @POST("auth/check-ktp")
    suspend fun checkKtpNumberAvailability(
        @Body ktpNumberCheckRequest: KtpNumberCheckRequest
    ): KtpNumberCheckResponse

    @POST("auth/login")
    suspend fun login(
        @Body loginRequest: LoginRequest,
    ): LoginResponse

    @GET("transaction/mutations")
    suspend fun getAllMutations(
        @Header("Authorization") token: String,
        @Query("accountNumber") accountNumber: String
    ): MutationsGetResponse

    @GET("transaction/mutation/date")
    suspend fun getMutationsByDate(
        @Header("Authorization") token: String,
        @Query("accountNumber") accountNumber: String,
        @Query("startDate") startDate: String,
        @Query("endDate") endDate: String,
        @Query("type") type: String,
    ): MutationsGetResponse

    @GET("transaction/mutation")
    suspend fun getMutationById(
        @Header("Authorization") token: String,
        @Query("id") id: String
    ): MutationGetResponse

    @GET("notification/")
    suspend fun getNotifications(
        @Header("Authorization") token: String
    ): List<NotificationGetResponse>

    @POST("send-otp")
    suspend fun getOtp(
        @Body otpGetRequest: OtpGetRequest
    ): OtpResponse

    @POST("verify-otp")
    suspend fun verifyOtp(
        @Body otpVerifyRequest: OtpVerifyRequest
    ): OtpResponse

    @POST("auth/check-phone-number")
    suspend fun checkPhoneNumberAvailability(
        @Body phoneNumberCheckRequest: PhoneNumberCheckRequest
    ): PhoneNumberCheckResponse

    @POST("auth/register")
    suspend fun register(
        @Body registerRequest: RegisterRequest
    ): RegisterResponse

    @GET("account-list")
    suspend fun getSavedAccounts(
        @Header("Authorization") token: String
    ): SavedAccountsGetResponse

    @POST("transaction/transfer")
    suspend fun transfer(
        @Header("Authorization") token: String,
        @Body transferRequest: TransferRequest
    ): TransferResponse

    @GET("user/me")
    suspend fun getUserData(
        @Header("Authorization") token: String
    ): UserGetResponse
}