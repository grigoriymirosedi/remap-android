package com.example.profile.store

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.ui.PointsCard
import com.example.core.uikit.RemapAppTheme
import com.example.core.uikit.RemapTheme

@Composable
internal fun TabItemEcoStore(
    modifier: Modifier = Modifier,
    storeItems: List<StoreItem>,
    shape: Shape = RemapAppTheme.shape.medium,
) {
    LazyColumn(
        modifier = modifier.padding(vertical = 4.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(storeItems) { storeItem ->
            Card(
                modifier = modifier
                    .fillMaxWidth()
                    .height(150.dp),
                colors = CardDefaults.cardColors(
                    containerColor = storeItem.backgroundColor
                ),
                shape = shape
            ) {
                Row(
                    modifier = modifier.padding(
                        vertical = 8.dp,
                        horizontal = 8.dp
                    ),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        modifier = Modifier.fillMaxHeight().weight(3f),
                        verticalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Text(
                            text = storeItem.title,
                            style = RemapAppTheme.typography.subheading1.copy(color = Color.White)
                        )
                        Text(
                            text = storeItem.description,
                            style = RemapAppTheme.typography.bodytext2.copy(color = Color.White)
                        )
                    }
                    Box(modifier = modifier.weight(3f)) {
                        Image(
                            modifier = Modifier.fillMaxSize(),
                            painter = painterResource(storeItem.image),
                            contentDescription = null
                        )
                        PointsCard(
                            modifier = modifier.align(Alignment.BottomEnd),
                            pointsAmount = storeItem.price.toString()
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TabItemStorePreview() {
    RemapTheme {
        TabItemEcoStore(
            storeItems = storeItems
        )
    }
}