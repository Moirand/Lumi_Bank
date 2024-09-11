package com.example.common

import android.content.Context
import android.widget.Toast

fun makeToast(context: Context, message: String?) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}