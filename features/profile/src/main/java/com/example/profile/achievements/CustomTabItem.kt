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
    val screen: @Composable () -> Unit
)

val requests = listOf(
    RequestItem(
        requestNumber = UUID.randomUUID().toString().take(10),
        requestStatus = 0,
        requestTitle = "Подари дереву жизнь",
        requestDate = "22.04.2025"
    ),

    RequestItem(
        requestNumber = UUID.randomUUID().toString().take(10),
        requestStatus = 1,
        requestTitle = "Подари дереву жизнь",
        requestDate = "22.04.2025"
    ),

    RequestItem(
        requestNumber = UUID.randomUUID().toString().take(10),
        requestStatus = 2,
        requestTitle = "Подари дереву жизнь",
        requestDate = "22.04.2025"
    )
)

internal var tabs = listOf(
    CustomTabItem(
        text = "Достижения",
        screen = { TabItemUserAchievements(achievements = achievements) }
    ),
    CustomTabItem(
        text = "Магазин",
        screen = { TabItemEcoStore(
            storeItems = storeItems
        ) }
    ),
    CustomTabItem(
        text = "Заявки",
        badgesCount = EMPTY_BADGES_COUNT,
        screen = { TabItemUserRequests(requests = requests) }
    )
)


