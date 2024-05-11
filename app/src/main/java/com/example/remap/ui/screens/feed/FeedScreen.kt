package com.example.remap.ui.screens.feed

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kevinnzou.web.WebView
import com.kevinnzou.web.rememberWebViewState

@Composable
fun FeedScreen(modifier: Modifier = Modifier) {
    val state = rememberWebViewState(url = "https://journal.tinkoff.ru/flows/ecology/")
    WebView(state = state)
}