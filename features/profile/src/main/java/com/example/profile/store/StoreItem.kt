package com.example.profile.store

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

data class StoreItem(
    val title: String,
    val description: String,
    val price: Int,
    val image: Int,
    val backgroundColor: Color
)

val storeItems = listOf(
    StoreItem(
        title = "Подари дереву жизнь",
        description = "Обменяй баллы на саженец, который высадят в экологическом парке",
        price = 600,
        image = com.example.ui.R.drawable.plant_tree_mascot,
        backgroundColor = Color(0xff59ba53)
    ),
    StoreItem(
        title = "Крылатый домик",
        description = "Подари пернатым друзьям безопасный дом!",
        price = 450,
        image = com.example.ui.R.drawable.birdhouse_mascot,
        backgroundColor = Color(0xff9b7a59)
    )
)
