package com.example.events.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun EventScreenRoute(
    modifier: Modifier = Modifier,
    viewModel: EventViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    EventScreen(
        modifier = modifier,
        uiState = uiState
    )
}