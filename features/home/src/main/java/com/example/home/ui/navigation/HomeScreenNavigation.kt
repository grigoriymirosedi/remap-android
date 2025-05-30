package com.example.home.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.home.models.RecyclePoint
import com.example.home.ui.HomeScreenRoute
import java.util.Objects

const val HOME_SCREEN_ROUTE="home_screen"
const val MAP_SCREEN_ROUTE="map_screen"

fun NavController.navigateToHomeScreen() = navigate(HOME_SCREEN_ROUTE) {
    popUpTo(HOME_SCREEN_ROUTE) {
        inclusive = true
    }
}


fun NavController.navigateToMapScreen() = navigate(MAP_SCREEN_ROUTE) {
    popUpTo(MAP_SCREEN_ROUTE) {
        inclusive = true
    }
}


inline fun <reified T: Any> NavGraphBuilder.homeScreen(
    noinline navigateToMap: (Double, Double) -> Unit
) {
    composable<T> {
        HomeScreenRoute(
            navigateToMap = navigateToMap
        )
    }
}