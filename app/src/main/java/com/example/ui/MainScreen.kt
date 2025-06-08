package com.example.ui

import android.Manifest
import android.content.pm.PackageManager
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.AuthViewModel
import com.example.navigation.AppNavHost

@Composable
fun MainScreen(
    authViewModel: AuthViewModel = hiltViewModel(),
) {
    val navController = rememberNavController()
    val isAuthorized by authViewModel.authState.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if(isAuthorized != null) {
                BottomNavigationBar(navController = navController)
            }
        }
    ) {
        AppNavHost(
            navController = navController,
            paddingValues = it,
            modifier = Modifier,
            authViewModel = authViewModel,
            isAuthorized = isAuthorized != null
        )
    }
}
