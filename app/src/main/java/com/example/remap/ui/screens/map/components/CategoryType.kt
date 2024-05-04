package com.example.remap.ui.screens.map.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CleanHands
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.CopyAll
import androidx.compose.material.icons.filled.Hardware
import androidx.compose.material.icons.outlined.Battery3Bar
import androidx.compose.material.icons.outlined.CatchingPokemon
import androidx.compose.material.icons.outlined.Checkroom
import androidx.compose.material.icons.outlined.Dangerous
import androidx.compose.material.icons.outlined.DirectionsCar
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material.icons.outlined.Lightbulb
import androidx.compose.material.icons.outlined.LocalCarWash
import androidx.compose.material.icons.outlined.QuestionMark
import androidx.compose.material.icons.outlined.Science
import androidx.compose.material.icons.outlined.TireRepair
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

enum class CategoryType(val type: Int, val sourceImage: ImageVector, val sourceName: String, val text: String) {
    PAPER(type = 0, sourceImage = Icons.Filled.CopyAll, sourceName = "paper", text = "Бумага"),
    PLASTIC(type = 1, sourceImage = Icons.Filled.CleanHands, sourceName = "plastic", text = "Пластик"),
    GLASS(type = 2, sourceImage = Icons.Outlined.Science, sourceName = "glass", text = "Стекло"),
    METAL(type = 3, sourceImage = Icons.Filled.Hardware, sourceName = "metal", text = "Металл"),
    TETRA_PACK(type = 4, sourceImage = Icons.Outlined.Inventory2, sourceName = "tetra_pack", text = "Тетра-пак"),
    CLOTHES(type = 5, sourceImage = Icons.Outlined.Checkroom, sourceName = "clothes", text = "Одежда"),
    LAMPS(type = 6, sourceImage = Icons.Outlined.Lightbulb, sourceName = "lamps", text = "Лампочки"),
    CAPS(type = 7, sourceImage = Icons.Outlined.CatchingPokemon, sourceName = "caps", text = "Крышечки"),
    TECH(type = 8, sourceImage = Icons.Outlined.DirectionsCar, sourceName = "tech", text = "Техника"),
    BATTERIES(type = 9, sourceImage = Icons.Outlined.Battery3Bar, sourceName = "batteries", text = "Батарейки"),
    TIRES(type = 10, sourceImage = Icons.Outlined.TireRepair, sourceName = "tires", text = "Шины"),
    DANGEROUS(type = 11, sourceImage = Icons.Outlined.Dangerous, sourceName = "dangerous", text = "Опасное"),
    OTHER(type = 12, sourceImage = Icons.Outlined.QuestionMark, sourceName = "other", text = "Другое")
}