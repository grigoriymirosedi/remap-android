package com.example.home.ui

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.models.RecyclePoint
import com.example.core.mock.MockData
import com.example.core.models.CategoryType
import com.example.core.ui.TextWithIcon
import com.example.core.ui.TitleText
import com.example.core.util.toCategoryType


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {

    val recyclePoints = viewModel.recyclePoints.collectAsState()
    val isLoading by remember { viewModel.isLoading }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        TitleText(text = "Привет!")

        HorizontalDivider(thickness = 2.dp)

//        TitleText(text = "Категории", fontSize = 20.sp)
//
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .horizontalScroll(rememberScrollState()),
//            horizontalArrangement = Arrangement.spacedBy(10.dp)
//        ) {
//            CategoryType.values().forEach {
//                FilterCard(modifier = Modifier.size(130.dp), filterType = it)
//            }
//        }

        TitleText(text = "Совет дня", fontSize = 20.sp)

        Card() {
            Row(
                modifier = Modifier.padding(8.dp, 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(3.dp)
            ) {
                Icon(
                    modifier = Modifier.size(60.dp),
                    imageVector = Icons.Filled.Lightbulb,
                    tint = Color(0xffffdf00),
                    contentDescription = null
                )
                Text(
                    modifier = Modifier.padding(end = 8.dp),
                    text = "Важно осознанно относиться к покупкам, иначе мы будем тушить огонь, в который сами же подливаем масло. Покупайте только то, что действительно нужно. Это избавляет от визуального мусора и хлама в доме, помогает сэкономить семейный бюджет и лучше узнать себя",
                    fontSize = 16.sp
                )
            }
        }

        TitleText(text = "Точки переработки", fontSize = 20.sp)

        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            recyclePoints.value.forEach {
                RecyclePointCard(modifier = Modifier.fillMaxWidth(), recyclePoint = it)
            }
        }
    }
}

@Composable
fun FilterCard(modifier: Modifier = Modifier, filterType: CategoryType) {
    Card(modifier = modifier) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp, vertical = 4.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Icon(modifier = Modifier.size(70.dp), imageVector = filterType.sourceImage, contentDescription = null)
            Text(text = filterType.text, fontSize = 18.sp)
        }
    }
}

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

@Preview(showBackground = true)
@Composable
fun FilterCardPreview(filterType: CategoryType = CategoryType.CLOTHES) {
    FilterCard(filterType = filterType)
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}