package com.example.common

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast

fun makeToast(context: Context, message: String?) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}

@SuppressLint("DefaultLocale")
fun String.toRupiah(): String =
    "Rp " + String.format("%,d", this.toDouble().toInt())

fun String.hideBalance(): String =
    "Rp " + "*".repeat(8)

fun String.hideAccountNumber(): String {
    val visibleDigits = 3
    val hiddenNum = "*".repeat(this.length - visibleDigits)
    val visibleNum = this.takeLast(visibleDigits)
    return "$hiddenNum$visibleNum"
}