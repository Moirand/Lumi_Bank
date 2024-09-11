package com.example.data.datasource.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.core.datasource.LocalDatasource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.single
import java.util.Date

class LocalDatasourceImpl(
    private val datastore: DataStore<Preferences>
) : LocalDatasource {
    companion object {
        private val TOKEN_KEY = stringPreferencesKey("jwt_token")
        private val TOKEN_RECEIPT_TIME_KEY = stringPreferencesKey("jwt_token_receipt_time")

        private val USER_NAME_KEY = stringPreferencesKey("user_name")
        private val USER_EMAIL_KEY = stringPreferencesKey("user_email")
        private val USER_PHONE_NUMBER_KEY = stringPreferencesKey("user_phone_number")
        private val ACCOUNT_NUMBER_KEY = stringPreferencesKey("account_number")
        private val ACCOUNT_PIN_KEY = stringPreferencesKey("account_pin")
        private val DATE_OF_BIRTH_KEY = stringPreferencesKey("date_of_birth")
        private val KTP_NUMBER_KEY = stringPreferencesKey("ktp_number")
        private val KTP_PHOTO_KEY = stringPreferencesKey("ktp_photo")
    }

    override suspend fun saveToken(token: String) {
        datastore.edit {
            it[TOKEN_KEY] = token
        }
    }

    override suspend fun loadToken(): Flow<String?> = flow {
        datastore.data.map {
            it[TOKEN_KEY]
        }.single()
    }

    override suspend fun deleteToken() {
        datastore.edit {
            it.remove(TOKEN_KEY)
            it.remove(TOKEN_RECEIPT_TIME_KEY)
        }
    }

    override suspend fun isTokenExpired(): Flow<Boolean> = flow {
        val expiredTime = 90 * 60 * 1000 // 1.5 jam dalam milidetik
        val currentTime = Date().time

        val tokenReceiptTime = datastore.data.map { preferences ->
            preferences[TOKEN_RECEIPT_TIME_KEY]?.toLongOrNull() ?: 0L
        }.first()

        emit(currentTime - tokenReceiptTime > expiredTime)
    }

    override suspend fun saveUserName(userName: String) {
        datastore.edit {
            it[USER_NAME_KEY] = userName
        }
    }

    override suspend fun loadUserName(): Flow<String?> = flow {
        datastore.data.map {
            it[USER_NAME_KEY]
        }.single()
    }

    override suspend fun saveUserEmail(userEmail: String) {
        datastore.edit {
            it[USER_EMAIL_KEY] = userEmail
        }
    }

    override suspend fun loadUserEmail(): Flow<String?> = flow {
        datastore.data.map {
            it[USER_EMAIL_KEY]
        }.single()
    }

    override suspend fun saveUserPhoneNumber(userPhoneNumber: String) {
        datastore.edit {
            it[USER_PHONE_NUMBER_KEY] = userPhoneNumber
        }
    }

    override suspend fun loadUserPhoneNumber(): Flow<String?> = flow {
        datastore.data.map {
            it[USER_PHONE_NUMBER_KEY]
        }.single()
    }

    override suspend fun saveAccountNumber(accountNumber: String) {
        datastore.edit {
            it[ACCOUNT_NUMBER_KEY] = accountNumber
        }
    }

    override suspend fun loadAccountNumber(): Flow<String?> = flow {
        datastore.data.map {
            it[ACCOUNT_NUMBER_KEY]
        }.single()
    }

    override suspend fun saveAccountPin(accountPin: String) {
        datastore.edit {
            it[ACCOUNT_PIN_KEY] = accountPin
        }
    }

    override suspend fun loadAccountPin(): Flow<String?> = flow {
        datastore.data.map {
            it[ACCOUNT_PIN_KEY]
        }.single()
    }

    override suspend fun saveDateOfBirth(dateOfBirth: String) {
        datastore.edit {
            it[DATE_OF_BIRTH_KEY] = dateOfBirth
        }
    }

    override suspend fun loadDateOfBirth(): Flow<String?> = flow {
        datastore.data.map {
            it[DATE_OF_BIRTH_KEY]
        }.single()
    }

    override suspend fun saveKtpNumber(ktpNumber: String) {
        datastore.edit {
            it[KTP_NUMBER_KEY] = ktpNumber
        }
    }

    override suspend fun loadKtpNumber(): Flow<String?> = flow {
        datastore.data.map {
            it[KTP_NUMBER_KEY]
        }.single()
    }

    override suspend fun saveKtpPhoto(ktpPhoto: String) {
        datastore.edit {
            it[KTP_PHOTO_KEY] = ktpPhoto
        }
    }

    override suspend fun loadKtpPhoto(): Flow<String?> = flow {
        datastore.data.map {
            it[KTP_PHOTO_KEY]
        }.single()
    }

    override suspend fun deleteUserData() {
        datastore.edit {
            it.remove(USER_NAME_KEY)
            it.remove(USER_EMAIL_KEY)
            it.remove(USER_PHONE_NUMBER_KEY)
            it.remove(ACCOUNT_NUMBER_KEY)
            it.remove(ACCOUNT_PIN_KEY)
            it.remove(DATE_OF_BIRTH_KEY)
            it.remove(KTP_NUMBER_KEY)
            it.remove(KTP_PHOTO_KEY)
        }
    }
}