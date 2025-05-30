package com.example.home.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HomeScreenRoute(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToMap: (Double, Double) -> Unit
) {

    val uiState by viewModel.uiState.collectAsState()

    HomeScreen(
        modifier = modifier,
        uiState = uiState,
        navigateToMap = navigateToMap,
        onEvent = viewModel::handleEvent
    )
}
