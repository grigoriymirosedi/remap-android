package com.example.ui

import android.annotation.SuppressLint
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.core.uikit.RemapAppTheme
import com.example.navigation.BottomScreens

@SuppressLint("RestrictedApi")
@Composable
fun BottomNavigationBar(navController: NavController) {
    val bottomScreens = remember {
        listOf(
            BottomScreens.Home,
            BottomScreens.Map,
            BottomScreens.Event,
            BottomScreens.Profile
        )
    }
    NavigationBar(
        containerColor = RemapAppTheme.colorScheme.brandDefault,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navController.currentBackStackEntryAsState().value?.destination
        bottomScreens.forEach { item ->
            val isSelected =
                currentRoute?.hierarchy?.any { it.hasRoute(item.route::class) } == true
            NavigationBarItem(
                colors = NavigationBarItemColors(
                    selectedIconColor = RemapAppTheme.colorScheme.brandActive,
                    selectedIndicatorColor = RemapAppTheme.colorScheme.brandActive.copy(alpha = 0.2f),
                    selectedTextColor = RemapAppTheme.colorScheme.brandActive,
                    unselectedIconColor = RemapAppTheme.colorScheme.brandTextDefault,
                    unselectedTextColor = RemapAppTheme.colorScheme.brandTextDefault,
                    disabledIconColor = RemapAppTheme.colorScheme.brandTextDefault,
                    disabledTextColor = RemapAppTheme.colorScheme.brandTextDefault
                ),
                icon = {
                    Icon(
                        painter = painterResource(item.icon),
                        contentDescription = null,
                        modifier = Modifier.alpha(0.5f)
                    )
                },
                label = { Text(item.name) },
                selected = isSelected,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id)
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}