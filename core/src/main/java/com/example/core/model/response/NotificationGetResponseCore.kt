package com.example.core.model.response

data class NotificationGetResponseCore(
    val id: String? = null,
    val title: String? = null,
    val body: String? = null,
    val sentAt: String? = null,
    val read: Boolean
)