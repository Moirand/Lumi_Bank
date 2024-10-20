package com.example.data.datasource.remote.response

import com.google.gson.annotations.SerializedName

data class NotificationGetResponse(
    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("body")
    val body: String? = null,

    @field:SerializedName("sentAt")
    val sentAt: String? = null,

    @field:SerializedName("read")
    val read: Boolean
)