package com.example.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Badge
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.ui.PointsCard
import com.example.core.uikit.RemapAppTheme
import com.example.core.uikit.RemapTheme
import com.example.profile.achievements.EMPTY_BADGES_COUNT
import com.example.profile.achievements.tabs
import com.example.ui.ProgressBar

@Composable
internal fun ProfileScreen(
    uiState: ProfileUiState,
) {
    when(uiState) {
        is ProfileUiState.Error -> ErrorScreen()
        is ProfileUiState.Loading -> ProgressBar()
        is ProfileUiState.Success -> ProfileScreenContent(
            username = uiState.username,
            email = uiState.email,
            totallyCollected = uiState.totallyCollected,
            userPoints = uiState.userPoints,
            requestsCount = uiState.requestsCount
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ProfileScreenContent(
    username: String,
    email: String,
    totallyCollected: Int,
    userPoints: Int,
    requestsCount: Int
) {

    var selectedTabIndex by remember { mutableStateOf(0) }
    tabs = if(requestsCount > 0) {
        tabs.map { tabItem ->
            if(tabItem.badgesCount == EMPTY_BADGES_COUNT) {
                tabItem.copy(badgesCount = requestsCount)
            } else tabItem
        }
    } else tabs

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp
            )
    ) {
        Row {
            Card(
                modifier = Modifier.size(80.dp),
                shape = CircleShape
            ) {
                Image(
                    painter = painterResource(com.example.ui.R.drawable.profile_icon),
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
            }
            Column(
                modifier = Modifier.padding(10.dp),
            ) {
                Text(
                    text = username,
                    style = RemapAppTheme.typography.heading1
                )
                Text(
                    text = email,
                    style = RemapAppTheme.typography.bodytext1.copy(color = RemapAppTheme.colorScheme.brandTextSecondary)
                )
            }
        }
        Spacer(modifier = Modifier.size(32.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            colors = CardDefaults.cardColors(containerColor = RemapAppTheme.colorScheme.brandBackground),
            elevation = CardDefaults.elevatedCardElevation(8.dp),
            shape = RemapAppTheme.shape.medium
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 24.dp),
                    painter = painterResource(com.example.ui.R.drawable.achievement_mascot),
                    contentDescription = null
                )
                Column(modifier = Modifier.padding(end = 4.dp)) {
                    Text(
                        text = "Вы сдали",
                        style = RemapAppTheme.typography.heading2.copy(fontWeight = FontWeight.SemiBold)
                    )
                    Text(
                        text = totallyCollected.toString() + " единиц",
                        style = RemapAppTheme.typography.heading2.copy(color = RemapAppTheme.colorScheme.brandActive)
                    )
                    Text(
                        text = "за всё время участия. Продолжайте в том же духе!",
                        style = RemapAppTheme.typography.heading2.copy(fontWeight = FontWeight.SemiBold)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.size(24.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            colors = CardDefaults.cardColors(containerColor = RemapAppTheme.colorScheme.brandActive),
            shape = RemapAppTheme.shape.medium
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Начислено баллов",
                    color = RemapAppTheme.colorScheme.brandDefault,
                    style = RemapAppTheme.typography.subheading1
                )
                PointsCard(
                    pointsAmount = userPoints.toString()
                )
            }
        }
        Spacer(modifier = Modifier.size(8.dp))

        Column {
            SecondaryTabRow(
                selectedTabIndex = selectedTabIndex,
                containerColor = RemapAppTheme.colorScheme.brandBackground,
                indicator = {
                    TabRowDefaults.SecondaryIndicator(
                        modifier = Modifier.tabIndicatorOffset(selectedTabIndex, matchContentSize = false),
                        color = RemapAppTheme.colorScheme.brandActive
                    )
                },
                contentColor = RemapAppTheme.colorScheme.brandTextDefault,
                modifier = Modifier.fillMaxWidth()
            ) {
                tabs.forEachIndexed { index, tabItem ->
                    Tab(
                        selected = index == selectedTabIndex,
                        onClick = {
                            selectedTabIndex = index
                        },
                        text = {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                if (tabItem.badgesCount > 0) {
                                    Text(
                                        modifier = Modifier.padding(6.dp),
                                        text = tabItem.text,
                                        style = RemapAppTheme.typography.subheading2.copy(fontWeight = FontWeight.SemiBold)
                                    )

                                    Badge {
                                        Text(
                                            text = tabItem.badgesCount.toString(),
                                        )
                                    }
                                } else {
                                    Text(
                                        text = tabItem.text,
                                        style = RemapAppTheme.typography.subheading2.copy(fontWeight = FontWeight.SemiBold)
                                    )
                                }
                            }
                        }
                    )
                }
            }
        }
        tabs[selectedTabIndex].screen()
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileScreenContentPreview() {
    val username = "Григорий"
    val email = "grigoriymirosedi@gmail.com"
    val totallyCollected = 52
    val userPoints = 1250
    val requestsCount = 4
    RemapTheme {
        ProfileScreenContent(
            username = username,
            email = email,
            totallyCollected = totallyCollected,
            userPoints = userPoints,
            requestsCount = requestsCount
        )
    }
}

@Composable
internal fun ErrorScreen() {

}

