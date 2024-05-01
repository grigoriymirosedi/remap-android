package com.example.remap.ui.utils

sealed class Screens(val route : String, val localizedName: String? = null) {
    object Home : Screens("home_route", localizedName = "Главный")
    object Map : Screens("map_route", localizedName = "Карта")
    object Calendar: Screens("calendar_route", localizedName = "События")
    object Feed : Screens("feed_route", localizedName = "Лента")
    object AddPlacemark: Screens("add_placemark")
}