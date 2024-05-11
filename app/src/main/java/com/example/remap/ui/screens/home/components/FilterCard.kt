package com.example.remap.ui.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.remap.ui.screens.map.components.CategoryType

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FilterCard(modifier: Modifier = Modifier, filterType: CategoryType) {
    Card(modifier = modifier) {
        Column(modifier = Modifier.fillMaxSize().padding(horizontal = 8.dp, vertical = 4.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Icon(modifier = Modifier.size(70.dp), imageVector = filterType.sourceImage, contentDescription = null)
            Text(text = filterType.text, fontSize = 18.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FilterCardPreview(filterType: CategoryType = CategoryType.CLOTHES) {
    FilterCard(filterType = filterType)
}