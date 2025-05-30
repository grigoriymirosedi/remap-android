package com.example.profile.achievements

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.example.profile.R

data class Achievement(
    val name: String,
    @DrawableRes val picture: Int,
)

internal val achievements = listOf(
    Achievement(
        "Зелёный старт",
        com.example.ui.R.drawable.winner_mascot,
    ),
    Achievement(
        "Чистый дебют",
        com.example.ui.R.drawable.staring_shy_mascot,
    ),
    Achievement(
        "С чистого листа",
        com.example.ui.R.drawable.smart_mascot,
    ),
    Achievement(
        "Рециклер",
        com.example.ui.R.drawable.running_mascot,
    ),
)