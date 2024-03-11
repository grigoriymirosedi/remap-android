package com.example.remap.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
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
            navController = navController
        ) {
            composable(Screens.Home.route) {
                //Replace with specific screen
                SampleScreen( "Главный экран")
            }

            composable(Screens.Map.route) {
                //Replace with specific screen
                SampleScreen( "Карта")
            }

            composable(Screens.Calendar.route) {
                //Replace with specific screen
                SampleScreen( "Мероприятия")
            }

            composable(Screens.Feed.route) {
                //Replace with specific screen
                SampleScreen( "Лента новостей")
            }
        }
    }
}