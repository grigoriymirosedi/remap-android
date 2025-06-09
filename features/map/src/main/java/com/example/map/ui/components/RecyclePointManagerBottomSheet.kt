package com.example.map.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Contacts
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.LocationCity
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.QuestionMark
import androidx.compose.material.icons.outlined.Title
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.core.ui.DefaultFilledButton
import com.example.core.ui.TitleText
import com.example.core.uikit.RemapAppTheme
import com.example.core.uikit.RemapTheme
import okhttp3.internal.isSensitiveHeader

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun RecyclePointManagerBottomSheet(
    modifier: Modifier = Modifier,
    scaffoldState: BottomSheetScaffoldState,
    isBottomSheetVisible: Boolean,
    initialRecyclePointAddress: String,
    scaffoldPeekHeight: Dp = 200.dp,
    sheetShape: Shape = RemapAppTheme.shape.medium,
    onAddRecyclePoint: (String, String, String, String, String, String, List<String>) -> Unit,
) {
    var recyclePointTitle by remember { mutableStateOf("") }
    var recyclePointDescription by remember { mutableStateOf("") }
    var recyclePointAddress by remember { mutableStateOf(initialRecyclePointAddress) }
    var recyclePointLocationHint by remember { mutableStateOf("") }
    var recyclePointWorkingHours by remember { mutableStateOf("") }
    var recyclePointContacts by remember { mutableStateOf("") }

    val selectedCategories = remember { mutableStateListOf<String>() }

    LaunchedEffect(initialRecyclePointAddress) {
        recyclePointAddress = initialRecyclePointAddress
    }

    if (isBottomSheetVisible) {
        BottomSheetScaffold(
            sheetContainerColor = RemapAppTheme.colorScheme.brandDefault,
            scaffoldState = scaffoldState,
            sheetPeekHeight = scaffoldPeekHeight,
            sheetShape = sheetShape,
            sheetContent = {
                LazyColumn(
                    modifier = Modifier.padding(
                        horizontal = 8.dp,
                        vertical = 6.dp
                    ),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    item {
                        TitleText(text = "Новая точка переработки")
                        Spacer(modifier = Modifier.size(8.dp))
                        OutlinedTextField(
                            value = recyclePointTitle,
                            onValueChange = {
                                recyclePointTitle = it
                            },
                            label = { Text(text = "Название пункта переработки") },
                            singleLine = true,
                            trailingIcon = {
                                Icon(
                                    imageVector = Icons.Outlined.Title,
                                    contentDescription = null
                                )
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                        OutlinedTextField(
                            value = recyclePointDescription,
                            onValueChange = {
                                recyclePointDescription = it
                            },
                            label = { Text(text = "Описание") },
                            singleLine = true,
                            trailingIcon = {
                                Icon(
                                    imageVector = Icons.Outlined.Description,
                                    contentDescription = null
                                )
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                        OutlinedTextField(
                            value = recyclePointAddress,
                            onValueChange = {
                                recyclePointAddress = it
                            },
                            label = { Text(text = "Адрес") },
                            singleLine = true,
                            trailingIcon = {
                                Icon(
                                    imageVector = Icons.Outlined.LocationCity,
                                    contentDescription = null
                                )
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                        OutlinedTextField(
                            value = recyclePointLocationHint,
                            onValueChange = {
                                recyclePointLocationHint = it
                            },
                            label = { Text(text = "Подсказка (рядом с чем находится, опционально)") },
                            singleLine = true,
                            trailingIcon = {
                                Icon(
                                    imageVector = Icons.Outlined.QuestionMark,
                                    contentDescription = null
                                )
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                        OutlinedTextField(
                            value = recyclePointContacts,
                            onValueChange = {
                                recyclePointContacts = it
                            },
                            label = { Text(text = "Контакты (опционально)") },
                            singleLine = true,
                            trailingIcon = {
                                Icon(
                                    imageVector = Icons.Outlined.Contacts,
                                    contentDescription = null
                                )
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                        OutlinedTextField(
                            value = recyclePointWorkingHours,
                            onValueChange = {
                                recyclePointWorkingHours = it
                            },
                            label = { Text(text = "Время работы") },
                            singleLine = true,
                            trailingIcon = {
                                Icon(
                                    imageVector = Icons.Outlined.CalendarMonth,
                                    contentDescription = null
                                )
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    item {
                        FlowRow(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            CategoryFilter.entries.forEach { category ->
                                CategoryFilterChip(
                                    categoryFilter = category,
                                    onClick = { categoryName, selected ->
                                        if (selectedCategories.contains(categoryName.toCategoryDTOFormat()) && !selected) {
                                            selectedCategories.remove(categoryName.toCategoryDTOFormat())
                                        } else {
                                            selectedCategories.add(category.rawNumber)
                                        }
                                    }
                                )
                            }
                        }
                    }

                    item {
                        DefaultFilledButton(
                            text = "Добавить",
                            onClick = {
                                onAddRecyclePoint(
                                    recyclePointTitle,
                                    recyclePointDescription,
                                    recyclePointAddress,
                                    recyclePointLocationHint,
                                    recyclePointContacts,
                                    recyclePointWorkingHours,
                                    selectedCategories.toList()
                                )
                                selectedCategories.clear()
                            },
                        )
                    }
                }
            }
        ) {}
    }
}

fun String.toCategoryDTOFormat(): String {
    return when (this) {
        "Бумага" -> "0"
        "Пластик" -> "1"
        "Стекло" -> "2"
        "Металл" -> "3"
        "Тетра-пак" -> "4"
        "Одежда" -> "5"
        "Лампочки" -> "6"
        "Крышечки" -> "7"
        "Техника" -> "8"
        "Батарейки" -> "9"
        "Шины" -> "10"
        "Опасное" -> "11"
        else -> "12"
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun RecyclePointManagerBottomSheetPreview() {
    RemapTheme {
        RecyclePointManagerBottomSheet(
            isBottomSheetVisible = true,
            scaffoldState = rememberBottomSheetScaffoldState(),
            initialRecyclePointAddress = "",
            onAddRecyclePoint = { _, _, _, _, _, _, _-> },
        )
    }
}