package com.example.data.datasource.remote.network

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiConfig {
    val BASE_URL = "https://lumibank-backend-edqo6jv53q-et.a.run.app/api/"

    fun provideApiService(context: Context): ApiService =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .client(provideOkhttpClient(context))
            .build()
            .create(ApiService::class.java)

    private fun provideOkhttpClient(context: Context): OkHttpClient =
        OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(provideHttpLoggingInterceptor())
            .addInterceptor(provideChuckerInterceptor(context))
            .build()

    private fun provideHttpLoggingInterceptor(): Interceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    private fun provideChuckerInterceptor(context: Context): Interceptor =
        ChuckerInterceptor.Builder(context).build()

//    private fun provideAuthorizationInterceptor(token: String): Interceptor =
//        Interceptor { chain ->
//            val request = chain.request().newBuilder()
//                .addHeader("Authorization", "Bearer $token")
//                .build()
//            chain.proceed(request)
//        }
}