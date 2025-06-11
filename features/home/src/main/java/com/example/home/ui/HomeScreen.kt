package com.example.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.ui.CollectedRecyclableItemCard
import com.example.core.ui.EventCard
import com.example.core.ui.MascotInfoCard
import com.example.core.ui.PointsCard
import com.example.core.ui.RecyclePointsNearbyCard
import com.example.core.ui.TitleText
import com.example.core.uikit.RemapAppTheme
import com.example.core.uikit.RemapTheme
import com.example.home.models.CollectedItem
import com.example.home.models.HomeUiState
import com.example.home.models.ProfileInfo
import com.example.home.models.RecycleEvent
import com.example.home.models.RecyclePoint
import com.example.home.models.State
import com.example.ui.ProgressBar

@Composable
internal fun HomeScreen(
    modifier: Modifier = Modifier,
    uiState: State,
    navigateToMap: (Double, Double) -> Unit,
    onEvent: (HomeUiEvent) -> Unit
) {

    when(uiState) {
        is State.Loading -> ProgressBar(modifier = modifier.fillMaxSize())
        is State.Error -> ErrorMessage(uiState.message)
        is State.Success -> {
            HomeScreenContent(
                modifier = modifier,
                state = uiState.data,
                navigateToMap = navigateToMap,
                onEvent = onEvent
            )
        }
    }
}

@Composable
private fun HomeScreenContent(
    modifier: Modifier = Modifier,
    state: HomeUiState,
    navigateToMap: (Double, Double) -> Unit,
    onEvent: (HomeUiEvent) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(RemapAppTheme.colorScheme.brandBackground)
            .padding(start = 16.dp, end = 16.dp, top = 12.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                TitleText(
                    text = "Привет, ${state.profileInfo.username}"
                )
                PointsCard(
                    pointsAmount = state.profileInfo.points.toString(),
                )
            }
        }

        item {
            MascotInfoCard(
                infoText = state.profileInfo.tip
            )
        }

        item {
            TitleText(
                text = "Сколько ты собрал",
                style = RemapAppTheme.typography.subheading1
            )
        }

        item {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(state.profileInfo.collectedItems) { collectedItem ->
                    CollectedRecyclableItemCard(
                        image = collectedItem.image,
                        imageColor = collectedItem.imageColor,
                        headerText = collectedItem.headerText,
                        recycledCountText = collectedItem.quantity.toString()
                    )
                }
            }
        }

        item {
            TitleText(
                text = "Точки перереработки рядом",
                style = RemapAppTheme.typography.subheading1
            )
        }

        item {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            )
            {
                items(state.recyclePointsNearby) { recyclePoint ->
                    RecyclePointsNearbyCard(
                        title = recyclePoint.name,
                        address = recyclePoint.address,
                        latitude = recyclePoint.latitude,
                        longitude = recyclePoint.longitude,
                        onClick = { navigateToMap(recyclePoint.latitude, recyclePoint.longitude) }
                    )
                }
            }
         }

        item {
            TitleText(
                text = "Ближайшие мероприятия",
                style = RemapAppTheme.typography.subheading1
            )
        }

        item {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(state.upcomingEvents) {upcomingEvent ->
                    EventCard(
                        imageURL = upcomingEvent.imageUrl,
                        title = upcomingEvent.title,
                        date = upcomingEvent.date,
                        time = upcomingEvent.time,
                        location = upcomingEvent.location,
                        onClick = { onEvent(HomeUiEvent.toggleUpcomingEventDetails(upcomingEvent))}
                    )
                }
            }
        }
    }
}



@Composable
fun ErrorMessage(errorMessage: String) {
    Text(errorMessage)
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenContentPreview() {

    val profileInfo = ProfileInfo(
        username = "Григорий",
        points = 350,
        tip = "Привет, я - Зелёная Клякса - твой экологический помощник в правильной переработке вторсырья"
    )

    val collectedItems: List<CollectedItem> = listOf(
        CollectedItem.Battery(quantity = 13),
        CollectedItem.Plastic(quantity = 11),
        CollectedItem.Paper(quantity = 6)
    )

    val recyclePoints = listOf(
        RecyclePoint(id = "", name = "Мехматовская мусорка", address = "ул. Мильчакова 8а", latitude = 47.216686, longitude = 39.628649, acceptedItems = emptyList(), workingHours =  "Круглосуточно", phoneNumber = "79999999", imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTPUbgWmbBYmUvYCgmvTjToqEQuLCAH8k5rIA&s"),
        RecyclePoint(id = "", name = "Мехматовская мусорка", address = "ул. Мильчакова 8а", latitude = 47.216686, longitude = 39.628649, acceptedItems = emptyList(), workingHours =  "Круглосуточно", phoneNumber = "79999999", imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTPUbgWmbBYmUvYCgmvTjToqEQuLCAH8k5rIA&s")
    )

    val upcomingEvents = listOf(
        RecycleEvent(id = "", title = "Субботник в ботаническом саду", date = "20 августа", time = "12:00", description = "Проведение первого мероприятия Ремапа", location = "переулок Ботанический Спуск, 7", imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSq42Q5fT68X6BzxLKttK9M7exYW6bEi1BX6Q&s"),
        RecycleEvent(id = "", title = "Субботник в ботаническом саду", date = "20 августа", time = "12:00", description = "Проведение первого мероприятия Ремапа", location = "переулок Ботанический Спуск, 7", imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSq42Q5fT68X6BzxLKttK9M7exYW6bEi1BX6Q&s")
    )

    val mockUiState = HomeUiState(
        profileInfo = profileInfo,
        collectedItems = collectedItems,
        recyclePointsNearby = recyclePoints,
        upcomingEvents = upcomingEvents,
    )

    RemapTheme {
        HomeScreenContent(
            state = mockUiState,
            navigateToMap = { latitude, longitude -> },
            onEvent = {}
        )
    }
}