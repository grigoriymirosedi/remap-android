package com.example.remap.ui.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.remap.core.util.toCategoryType
import com.example.remap.domain.models.RecyclePoint
import com.example.remap.ui.utils.MockData
import com.example.remap.ui.utils.TextWithIcon
import com.example.remap.ui.utils.TitleText

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun RecyclePointCard(modifier: Modifier = Modifier, recyclePoint: RecyclePoint) {
    Card(modifier = modifier) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            TitleText(text = recyclePoint.name)
            Text(text = recyclePoint.description, fontSize = 16.sp)
            TextWithIcon(
                modifier = Modifier.fillMaxWidth(),
                text = recyclePoint.address,
                icon = Icons.Outlined.LocationOn,
                contentColor = Color.Gray,
                fontSize = 16.sp,
            )
            FlowRow(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                recyclePoint.categories.forEach {
                    SuggestionChip(onClick = { /*TODO*/ }, label = { Text(text = it.toCategoryType(), fontSize = 14.sp) })
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecyclePointCardPreview(recyclePoint: RecyclePoint = MockData.mockRecyclePointData) {
    RecyclePointCard(recyclePoint = recyclePoint)
}
