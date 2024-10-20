package com.example.data.datasource.remote

import com.example.core.model.request.AccountSaveRequest
import com.example.core.model.request.EmailCheckRequest
import com.example.core.model.request.ForgotPasswordRequest
import com.example.core.model.request.KtpNumberCheckRequest
import com.example.core.model.request.LoginRequest
import com.example.core.model.request.OtpGetRequest
import com.example.core.model.request.OtpVerifyRequest
import com.example.core.model.request.PhoneNumberCheckRequest
import com.example.core.model.request.RegisterRequest
import com.example.core.model.request.TransferRequest
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

object DummyResponse {
    fun saveAccount(token: String, accountSaveRequest: AccountSaveRequest): AccountSaveResponse =
        AccountSaveResponse(
            message = "Akun berhasil disimpan",
            success = true
        )

    fun getAccounts(token: String): List<AccountsResponse> =
        listOf(
            AccountsResponse(
                id = "21e192fd-2910-48fe-85f0-f31b6ff8e56e",
                userId = "",
                userName = "Watermelon",
                accountNumber = "1234567890",
                bankName = "Lumi Bank"
            ),

            AccountsResponse(
                id = "11e192fd-2910-48fe-85f0-f31b6ff8e56e",
                userId = "",
                userName = "Tuyul Imut",
                accountNumber = "7272924994",
                bankName = "Lumi Bank"
            ),
        )

    fun getBalance(token: String, accountNumber: String): BalanceGetResponse =
        BalanceGetResponse(
            data = 3000000.0,
            message = "Saldo berhasil dimuat",
            success = true
        )

    fun checkEmailAvailability(emailCheckRequest: EmailCheckRequest): EmailCheckResponse =
        EmailCheckResponse(
            data = "",
            success = true
        )

    fun sendForgetPassword(sendForgetPassRequest: ForgotPasswordRequest): ForgotPasswordResponse =
        ForgotPasswordResponse(
            status = true,
            message = "Success"
        )

    fun validateForgetPassword(validateForgetPass: ForgotPasswordRequest): ForgotPasswordResponse =
        ForgotPasswordResponse(
            status = true,
            message = "Success"
        )

    fun setNewPassword(setNewPassRequest: ForgotPasswordRequest): ForgotPasswordResponse =
        ForgotPasswordResponse(
            status = true,
            message = "Success"
        )

    fun checkKtpNumberAvailability(ktpNumberCheckRequest: KtpNumberCheckRequest): KtpNumberCheckResponse =
        KtpNumberCheckResponse(
            data = "",
            success = true
        )

    fun login(loginRequest: LoginRequest): LoginResponse =
        LoginResponse(
            data = LoginData(
                name = "Andre",
                email = "andreansyah1815@gmail.com",
                jwtToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9",
                refreshToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9"
            ),
            message = "Login Berhasil",
            success = true
        )

    fun getAllMutations(token: String, accountNumber: String): MutationsGetResponse =
        MutationsGetResponse(
            data = listOf(
                MutationData(
                    id = "ad358280-316d-492a-86d1-a288b1ffcfc2",
                    usernameFrom = "Andre",
                    accountFrom = "1234567890",
                    usernameTo = "Tuyul Imut",
                    accountTo = "7272924994",
                    balance = 3000000.0,
                    amount = 2000.0,
                    datetime = "2024-08-23T13:23:11.623804",
                    description = "Testing",
                    type = "transfer",
                    status = "completed"
                ),
                MutationData(
                    id = "f59a58d0-1398-4879-9319-8ca2db163710",
                    usernameFrom = "Tuyul Imut",
                    accountFrom = "7272924994",
                    usernameTo = "Andre",
                    accountTo = "1234567890",
                    balance = 3000000.0,
                    amount = 5000.0,
                    datetime = "2024-08-22T13:23:11.623804",
                    description = "Testing",
                    type = "transfer",
                    status = "completed"
                ),
            ),
            message = "Mutasi berhasil dimuat",
            success = true
        )

    fun getMutationsByDate(
        token: String,
        accountNumber: String,
        startDate: String,
        endDate: String,
        type: String
    ): MutationsGetResponse =
        MutationsGetResponse(
            data = listOf(
                MutationData(
                    id = "ad358280-316d-492a-86d1-a288b1ffcfc2",
                    usernameFrom = "Andre",
                    accountFrom = "1234567890",
                    usernameTo = "Tuyul Imut",
                    accountTo = "7272924994",
                    balance = 3000000.0,
                    amount = 2000.0,
                    datetime = "2024-08-23T13:23:11.623804",
                    description = "Testing",
                    type = "transfer",
                    status = "completed"
                )
            ),
            message = "Mutasi berhasil dimuat",
            success = true
        )

    fun getMutationById(token: String, id: String): MutationGetResponse =
        MutationGetResponse(
            data = MutationData(
                id = "ad358280-316d-492a-86d1-a288b1ffcfc2",
                accountFrom = "1234567890",
                usernameFrom = "Andre",
                accountTo = "7272924994",
                usernameTo = "Tuyul Imut",
                balance = 3000000.0,
                amount = 2000.0,
                datetime = "2024-08-23T13:23:11.623804",
                description = "Testing",
                type = "transfer",
                status = "completed"
            ),
            message = "Mutasi berhasil dimuat",
            success = true
        )

    fun getNotifications(token: String): List<NotificationGetResponse> =
        listOf(
            NotificationGetResponse(
                id = "asdasoas-ka1223n1las",
                title = "Transfer Keluar",
                body = "Anda melakukan transfer sebesar Rp 2000 ke Tuyul Imut",
                sentAt = "2024-08-23T13:23:11.623804",
                read = false
            ),
            NotificationGetResponse(
                id = "asjdabks-aksd12mlasd",
                title = "Transfer Masuk",
                body = "Anda menerima transfer sebesar Rp 5000 dari Tuyul Imut",
                sentAt = "2024-08-22T13:23:11.623804",
                read = false
            )
        )

    fun getOtp(otpGetRequest: OtpGetRequest): OtpResponse =
        OtpResponse(
            message = "OTP Berhasil dikirim, silakan periksa email anda",
            success = true
        )

    fun verifyOtp(otpVerifyRequest: OtpVerifyRequest): OtpResponse =
        OtpResponse(
            message = "OTP berhasil diverifikasi",
            success = true
        )

    fun checkPhoneNumberAvailability(phoneNumberCheckRequest: PhoneNumberCheckRequest): PhoneNumberCheckResponse =
        PhoneNumberCheckResponse(
            data = "",
            success = true
        )

    fun register(registerRequest: RegisterRequest): RegisterResponse =
        RegisterResponse(
            data = RegisterData(
                name = "Andre",
                email = "andreansyah1815@gmail.com",
                noHp = "08211111111",
                dateOfBirth = "2000-12-12 00:00:00.0",
                accountPin = "111111",
                accountNumber = "1234567890",
                noKtp = "8976664007813315",
                ektpPhoto = null
            ),
            message = "Akun berhasil dibuat",
            success = true
        )

    fun getSavedAccounts(token: String): SavedAccountsGetResponse =
        SavedAccountsGetResponse(
            data = listOf(
                SavedAccountGetData(
                    id = "21e192fd-2910-48fe-85f0-f31b6ff8e56e",
                    name = "Watermelon",
                    accountNumber = "1234567890",
                    destinationBank = "Lumi Bank"
                ),
                SavedAccountGetData(
                    id = "11e192fd-2910-48fe-85f0-f31b6ff8e56e",
                    name = "Tuyul Imut",
                    accountNumber = "7272924994",
                    destinationBank = "Lumi Bank"
                )
            ),
            message = "Akun tersimpan berhasil dimuat",
            success = true
        )

    fun transfer(token: String, transferRequest: TransferRequest): TransferResponse =
        TransferResponse(
            data = TransferData(
                id = "742f72bb-20d6-43a8-9d92-3f9a8adac0af",
                createdAt = "2024-08-25T04:40:14.066346754",
                referenceNumber = "000188609123",
                nameAccountFrom = "Andre",
                accountFrom = "1234567890",
                nameAccountTo = "Tuyul Imut",
                accountTo = "7272924994",
                destinationBank = "Lumi Bank",
                balance = 3000000.0,
                amount = 5000,
                datetime = "2024-08-25T04:40:24.066339571",
                description = "halo",
                type = "transfer",
                status = "completed"
            ),
            message = "Transfer berhasil",
            success = true
        )

    fun getUserData(token: String): UserGetResponse =
        UserGetResponse(
            data = UserData(
                name = "Andre",
                email = "andreansyah1815@gmail.com",
                noHp = "08211111111",
                dateOfBirth = "2000-12-12 00:00:00.0",
                accountNumber = "1234567890",
                accountPin = "111111",
                noKtp = "8976664007813315",
                ektpPhoto = null,
            ),
            message = "Data pengguna berhasil dimuat",
            success = true
        )
}