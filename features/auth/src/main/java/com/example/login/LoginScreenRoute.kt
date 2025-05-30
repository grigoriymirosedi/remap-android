package com.example.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.AuthEvent
import com.example.AuthViewModel

@Composable
fun LoginScreenRoute(
    onLoginComplete: () -> Unit,
    viewModel: AuthViewModel,
    onNavigateToRegister: () -> Unit
) {
    val isAuthorized by viewModel.authState.collectAsState()

    LaunchedEffect(isAuthorized) {
        if (isAuthorized != null) {
            onLoginComplete()
        }
    }

    LoginScreen(
        onLoginComplete = { username, password ->
            viewModel.handleEvent(AuthEvent.Login(username, password))
        },
        onNavigateToRegister = onNavigateToRegister
    )
}