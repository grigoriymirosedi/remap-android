package com.example.map.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BatteryFull
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.ElectricalServices
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.Inbox
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.LocalDrink
import androidx.compose.material.icons.filled.RadioButtonChecked
import androidx.compose.material.icons.filled.RestoreFromTrash
import androidx.compose.material.icons.filled.Science
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.Warning
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

enum class CategoryFilter(
    val displayName: String,
    val icon: ImageVector,
    val rawNumber: String,
    val categoryColor: Color,
) {
    Paper(
        displayName = "Бумага",
        icon = Icons.Filled.Description,
        rawNumber = "0",
        categoryColor = Color(0xFFB0BEC5) // светло-серый / серо-голубой
    ),
    Plastic(
        displayName = "Пластик",
        icon = Icons.Filled.RestoreFromTrash,
        rawNumber = "1",
        categoryColor = Color(0xFFF57C00) // оранжевый
    ),
    Glass(
        displayName = "Стекло",
        icon = Icons.Filled.LocalDrink,
        rawNumber = "2",
        categoryColor = Color(0xFF00BCD4) // голубой / цвет бутылки
    ),
    Metal(
        displayName = "Металл",
        icon = Icons.Filled.Settings,
        rawNumber = "3",
        categoryColor = Color(0xFF9E9E9E) // серый / металл
    ),
    TetraPak(
        displayName = "Тетра-пак",
        icon = Icons.Filled.Inbox,
        rawNumber = "4",
        categoryColor = Color(0xFF8D6E63) // коричневый / цвет коробки
    ),
    Clothes(
        displayName = "Одежда",
        icon = Icons.Filled.ShoppingBag,
        rawNumber = "5",
        categoryColor = Color(0xFF7E57C2) // фиолетовый
    ),
    Bulbs(
        displayName = "Лампочки",
        icon = Icons.Filled.Lightbulb,
        rawNumber = "6",
        categoryColor = Color(0xFFFFEB3B) // жёлтый
    ),
    Caps(
        displayName = "Крышечки",
        icon = Icons.Filled.RadioButtonChecked,
        rawNumber = "7",
        categoryColor = Color(0xFF4DB6AC) // бирюзовый
    ),
    Electronics(
        displayName = "Техника",
        icon = Icons.Filled.ElectricalServices,
        rawNumber = "8",
        categoryColor = Color(0xFF3F51B5) // индиго
    ),
    Batteries(
        displayName = "Батарейки",
        icon = Icons.Filled.BatteryFull,
        rawNumber = "9",
        categoryColor = Color(0xFF388E3C) // зелёный
    ),
    Tires(
        displayName = "Шины",
        icon = Icons.Filled.DirectionsCar,
        rawNumber = "10",
        categoryColor = Color(0xFF212121) // почти чёрный / резина
    ),
    Hazardous(
        displayName = "Опасное",
        icon = Icons.Filled.Science,
        rawNumber = "11",
        categoryColor = Color(0xFFD32F2F) // ярко-красный
    ),
    Other(
        displayName = "Другое",
        icon = Icons.Filled.HelpOutline,
        rawNumber = "12",
        categoryColor = Color(0xFF9E9E9E) // серый
    );
}