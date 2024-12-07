package com.example.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.home.ui.HomeScreen
import com.example.map.ui.MapScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    paddingValues: PaddingValues,
    modifier: Modifier,
) {
    NavHost(
        modifier = modifier.padding(paddingValues),
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
            HomeScreen()
        }

        composable(Screens.Map.route) {
            MapScreen()
        }
        composable(Screens.Calendar.route) {
//                CalendarScreen()
        }

        composable(Screens.Feed.route) {
//                FeedScreen()
        }
    }
}
