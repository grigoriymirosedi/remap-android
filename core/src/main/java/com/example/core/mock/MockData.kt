package com.example.core.mock

import com.example.core.models.PlacemarkDetails
import com.example.core.models.RecyclePoint
import com.yandex.mapkit.geometry.Point

object MockData {
    val mockRecyclePointData = RecyclePoint(
        id = "@#%fg@4dsg@%",
        name = "Тестовая собрание у мехматовской мусорки",
        image = null,
        description = "Собрание 3-го и 4-го курса у мехматовской мусорки",
        contacts = "grigoriymirosedi@gmail.com",
        latitude = 47.216686,
        longitude = 39.628649,
        address = "ул. Мильчакова 8а",
        working_hours = "Круглосуточно",
        categories = listOf("1","2","3")
    )

    val mockPlacemarkDetails = PlacemarkDetails(
        details = "ул. Мильчакова 8а",
        coordinates = Point(47.216686, 39.628649)
    )
}