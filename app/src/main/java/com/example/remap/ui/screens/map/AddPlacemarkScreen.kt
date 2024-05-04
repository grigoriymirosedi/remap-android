package com.example.remap.ui.screens.map

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.FormatSize
import androidx.compose.material.icons.outlined.EditLocation
import androidx.compose.material.icons.outlined.FormatAlignLeft
import androidx.compose.material.icons.outlined.PermContactCalendar
import androidx.compose.material.icons.outlined.Today
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.remap.ui.screens.calendar.MapViewModel
import com.example.remap.ui.screens.map.components.CategoryType
import com.example.remap.ui.utils.CategoryFilterChip
import com.example.remap.ui.utils.TextWithIcon
import com.example.remap.ui.utils.TitleText

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AddPlacemarkScreen(
    viewModel: MapViewModel = hiltViewModel(),
    placeMarkDetails: String,
    latitude: Double,
    longitude: Double,
    onComplete: () -> Unit,
) {

    val context = LocalContext.current

    var placemarkName by rememberSaveable { mutableStateOf("") }
    var placemarkDescription by rememberSaveable { mutableStateOf("") }
    var placemarkContacts by rememberSaveable { mutableStateOf("") }
    var placemarkAddress by rememberSaveable { mutableStateOf(placeMarkDetails) }
    var placemarkWorkingHours by rememberSaveable { mutableStateOf("") }

    val (checkedState, onStateChange) = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        TitleText(text = "Новая точка переработки")

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.FormatSize,
                    contentDescription = null
                )
            },
            value = placemarkName,
            onValueChange = { placemarkName = it },
            label = { Text("Название") }
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.FormatAlignLeft,
                    contentDescription = null
                )
            },
            value = placemarkDescription,
            onValueChange = { placemarkDescription = it },
            label = { Text("Описание") }
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.PermContactCalendar,
                    contentDescription = null
                )
            },
            value = placemarkContacts,
            onValueChange = { placemarkContacts = it },
            label = { Text("Контакты") }
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.EditLocation,
                    contentDescription = null
                )
            },
            value = placemarkAddress,
            onValueChange = { placemarkAddress = it },
            label = { Text("Адрес") }
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = { Icon(imageVector = Icons.Outlined.Today, contentDescription = null) },
            value = placemarkWorkingHours,
            onValueChange = { placemarkWorkingHours = it },
            label = { Text("Часы работы") }
        )

        Spacer(modifier = Modifier.size(16.dp))

        TitleText(text = "Категории", fontSize = 20.sp)

        Spacer(modifier = Modifier.size(8.dp))

        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .toggleable(
                    value = checkedState,
                    onValueChange = { onStateChange(!checkedState) },
                    role = Role.Checkbox
                ),
            horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start)
        )
        {
            CategoryType.values().forEach {
                CategoryFilterChip(
                    categoryType = it,
                    onClick = {
                        viewModel.categoryType.value.add(it.sourceName)
                    },
                    onDissmiss = { viewModel.categoryType.value.remove(it.sourceName) }
                )
            }
        }

        Button(
            onClick = {
                viewModel.addRecyclePoint(
                    name = placemarkName,
                    image = null,
                    description = placemarkDescription,
                    contacts = placemarkContacts,
                    latitude = latitude,
                    longitude = longitude,
                    address = placemarkAddress,
                    working_hours = placemarkWorkingHours
                )
                onComplete()
                Toast.makeText(context, "Заявка отправлена на модерацию!", Toast.LENGTH_LONG).show()
            }) {
            TextWithIcon(text = "Добавить", icon = Icons.Filled.Add, contentColor = Color.White)
        }

    }
}