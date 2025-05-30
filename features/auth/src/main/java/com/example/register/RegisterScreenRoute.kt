package com.example.register

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.AuthEvent
import com.example.AuthViewModel

@Composable
fun RegisterScreenRoute(
    viewModel: AuthViewModel,
    onRegistrationComplete: () -> Unit
) {
    val isAuthorized by viewModel.authState.collectAsState()

    LaunchedEffect(isAuthorized) {
        if (isAuthorized != null) {
            onRegistrationComplete()
        }
    }

    RegisterScreen(
        onRegistrationComplete = { username, email, password ->
            viewModel.handleEvent(AuthEvent.Register(username = username, email = email, password = password))
        }
    )
}
