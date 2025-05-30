package com.example.profile.achievements

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.core.uikit.RemapAppTheme

@Composable
internal fun TabItemUserAchievements(
    modifier: Modifier = Modifier,
    achievements: List<Achievement>
) {
    LazyVerticalGrid(
        horizontalArrangement = Arrangement.SpaceEvenly,
        columns = GridCells.Adaptive(100.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(achievements) { item ->
            Column(modifier = Modifier.padding(vertical = 4.dp)) {
                Card(
                    modifier = Modifier.size(120.dp),
                    elevation = CardDefaults.elevatedCardElevation(6.dp),
                    colors = CardDefaults.cardColors(containerColor = RemapAppTheme.colorScheme.brandBackground),
                    shape = RemapAppTheme.shape.medium
                ) {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = painterResource(item.picture),
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                    )
                }
                Spacer(modifier = Modifier.size(4.dp))
                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = item.name,
                )
            }
        }
    }
}