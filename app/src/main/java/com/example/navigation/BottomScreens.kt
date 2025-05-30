package com.example.navigation

import com.example.remap.R
import kotlinx.serialization.Serializable

@Serializable
sealed class BottomScreens<T>(val name : String, val icon: Int, val route: T) {
    @Serializable
    data object Home: BottomScreens<HomeScreen>(name = "Главный", icon = R.drawable.outline_home_24, HomeScreen)
    @Serializable
    data object Map: BottomScreens<MapScreen>(name = "Карта", icon = R.drawable.ic_bottom_nav_map_24, route = MapScreen())
    @Serializable
    data object Event: BottomScreens<EventScreen>(name = "События", icon = R.drawable.ic_bottom_nav_events_24, route = EventScreen)
    @Serializable
    data object Profile: BottomScreens<ProfileScreen>(name = "Профиль", icon = R.drawable.ic_bottom_nav_profile_24, route = ProfileScreen)
}