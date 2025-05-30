package com.example.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.AuthViewModel
import com.example.events.ui.EventScreenRoute
import com.example.home.ui.HomeScreenRoute
import com.example.login.LoginScreenRoute
import com.example.map.ui.MapScreenRoute
import com.example.profile.ProfileScreenRoute
import com.example.register.RegisterScreenRoute
import kotlinx.serialization.Serializable

@Composable
fun AppNavHost(
    navController: NavHostController,
    paddingValues: PaddingValues,
    modifier: Modifier,
    authViewModel: AuthViewModel,
    isAuthorized: Boolean
) {
    val startDestination = if(isAuthorized) HomeScreen else LoginScreen

    NavHost(
        modifier = modifier.padding(paddingValues),
        startDestination = startDestination,
        navController = navController,
    ) {

        composable<HomeScreen> {
            HomeScreenRoute(
                navigateToMap = { latitude, longitude ->
                    val mapScreen = MapScreen(latitude = latitude, longitude = longitude)
                    navController.navigate(mapScreen) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }

        composable<MapScreen> { navBackStackEntry ->
            val data: MapScreen = navBackStackEntry.toRoute()
            MapScreenRoute(latitude = data.latitude, longitude = data.longitude)
        }

        composable<EventScreen>{
            EventScreenRoute()
        }

        composable<ProfileScreen> {
            ProfileScreenRoute()
        }

        composable<LoginScreen> {
            LoginScreenRoute(
                viewModel = authViewModel,
                onLoginComplete = {
                    navController.navigate(HomeScreen) {
                        popUpTo<LoginScreen> {
                            inclusive=true
                        }
                    }
                },
                onNavigateToRegister = {
                    navController.navigate(RegisterScreen)
                }
            )
        }

        composable<RegisterScreen> {
            RegisterScreenRoute(
                viewModel = authViewModel,
                onRegistrationComplete = {
                    navController.navigate(HomeScreen){
                        popUpTo<HomeScreen>()
                    }
                }
            )
        }
    }
}

@Serializable
object HomeScreen

@Serializable
data class MapScreen(val latitude: Double? = null, val longitude: Double? = null)

@Serializable
object EventScreen

@Serializable
object ProfileScreen

@Serializable
object LoginScreen

@Serializable
object RegisterScreen
