package com.example.map.ui.models

import com.example.models.AcceptedItemDTO

data class AcceptedItem(
    val id: String,
    val name: String
)

fun AcceptedItemDTO.toAcceptedItem() = AcceptedItem(
    id = id,
    name = name
)