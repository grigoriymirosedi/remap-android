package com.example.profile.requests

const val PENDING = 0
const val APPROVED = 1
const val REJECTED = 2

data class RequestItem(
    val requestNumber: String,
    val requestStatus: Int, // 0 - pending, 1 - approved, 2 - rejected
    val requestTitle: String,
    val requestDate: String,
)

