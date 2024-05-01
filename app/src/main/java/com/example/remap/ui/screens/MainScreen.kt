package com.example.remap.ui.screens

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.remap.core.util.toPlacemarkAddressFormat
import com.example.remap.core.util.toRightArgumentFormat
import com.example.remap.ui.screens.calendar.CalendarScreen
import com.example.remap.ui.screens.map.MapScreen
import com.example.remap.ui.screens.map.AddPlacemarkScreen
import com.example.remap.ui.utils.BottomNavigationBar
import com.example.remap.ui.utils.Screens

@Composable
fun MainScreen() {

    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavigationBar(navController = navController, backStackEntry = backStackEntry)
        }
    ) {
        NavHost(
            modifier = Modifier.padding(it),
            startDestination = Screens.Home.route,
            navController = navController,
            enterTransition = {
                EnterTransition.None
            },
            exitTransition = {
                ExitTransition.None
            }
        ) {
            composable(Screens.Home.route) {
                //Replace with specific screen
                SampleScreen( "Главный экран")
            }

            composable(Screens.Map.route) {
                //Replace with specific screen
                MapScreen(
                    onNavigateToAddPlacemarkScreen = { details, latitude, longitude ->
                        navController.navigate(
                            route = "add_placemark/${details.toRightArgumentFormat()}/$latitude/$longitude"
                        )
                    }
                )
            }

            composable(route = "add_placemark/{details}/{latitude}/{longitude}", arguments = listOf(
                navArgument(name = "details") { type = NavType.StringType },
                navArgument(name = "latitude") { type = NavType.FloatType },
                navArgument(name = "longitude") { type = NavType.FloatType }
            )) {backStackEntry ->
                val placemarkDetails = backStackEntry.arguments?.getString("details")?.toPlacemarkAddressFormat()
                val latitude = backStackEntry.arguments?.getFloat("latitude")
                val longitude = backStackEntry.arguments?.getFloat("longitude")
                AddPlacemarkScreen(
                    placeMarkDetails = placemarkDetails ?: "",
                    latitude = latitude!!.toDouble(),
                    longitude = longitude!!.toDouble()
                )
            }

            composable(Screens.Calendar.route) {
                //Replace with specific screen
                CalendarScreen()
            }

            composable(Screens.Feed.route) {
                //Replace with specific screen
                SampleScreen( "Лента новостей")
            }
        }
    }
}