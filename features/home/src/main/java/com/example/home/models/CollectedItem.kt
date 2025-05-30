package com.example.home.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BatteryChargingFull
import androidx.compose.material.icons.filled.RestoreFromTrash
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.models.CollectedItemDTO
import com.example.models.ProfileInfoDTO

sealed class CollectedItem(val itemType: String, val image: ImageVector, val imageColor: Color, val headerText: String, val quantity: Int) {
    class Plastic(itemType: String = "Plastic",  quantity: Int, headerText: String = "Сдано пластика", image: ImageVector = Icons.Filled.RestoreFromTrash, imageColor: Color = Color(0xff6ABE68)): CollectedItem(itemType, image, imageColor, headerText, quantity)
    class Paper(itemType: String = "Paper", quantity: Int, headerText: String = "Сдано макулатуры", image: ImageVector = Icons.Filled.WaterDrop, imageColor: Color = Color(0xff87ceeb)): CollectedItem(itemType, image, imageColor, headerText, quantity)
    class Battery(itemType: String = "Battery", quantity: Int, headerText: String = "Сдано батареек", image: ImageVector = Icons.Filled.BatteryChargingFull, imageColor: Color = Color(0xffFBCBD1)): CollectedItem(itemType, image, imageColor, headerText, quantity)
}

fun ProfileInfoDTO.convertToCollectedItem() = collectedUnits.first().toCollectedItem()

fun CollectedItemDTO.toCollectedItem() = listOf(
    CollectedItem.Battery(quantity = batteriesUnit),
    CollectedItem.Paper(quantity = paperUnit),
    CollectedItem.Plastic(quantity = plasticUnit)
)