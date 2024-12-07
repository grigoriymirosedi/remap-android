package com.example.ui

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.navigation.BottomNavigationItem

@Composable
fun BottomNavigationBar(navController: NavController, backStackEntry: NavBackStackEntry?) {
    NavigationBar {
        BottomNavigationItem().bottomNavigationItems().forEach { item ->

            val currentRoute = backStackEntry?.destination?.route

            NavigationBarItem(
                icon = { Icon(item.icon, null, modifier = Modifier.alpha(0.5f)) },
                label = { Text(item.label ?: item.route) },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}