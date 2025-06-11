package com.example.profile.requests

import com.example.models.RequestItemDTO
import java.time.LocalDate

const val PENDING = 0
const val APPROVED = 1
const val REJECTED = 2

data class RequestItem(
    val requestNumber: String,
    val requestStatus: Int, // 0 - pending, 1 - approved, 2 - rejected
    val address: String,
    val category: String,
    val requestTitle: String,
    val requestDate: String,
)

fun RequestItemDTO.toRequestItem() = RequestItem(
    requestNumber = requestNumber.take(10),
    requestStatus = status,
    address = address,
    requestTitle = title,
    category = category,
    requestDate = createdAt
)

