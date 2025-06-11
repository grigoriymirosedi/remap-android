package com.example.profile.achievements

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.profile.requests.RequestItem
import com.example.profile.requests.TabItemUserRequests
import com.example.profile.store.TabItemEcoStore
import com.example.profile.store.storeItems
import java.util.UUID

internal const val EMPTY_BADGES_COUNT = -1

data class CustomTabItem(
    val text: String,
    val icon: ImageVector? = null,
    val badgesCount: Int = 0,
    var screen: @Composable () -> Unit
)




