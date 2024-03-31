package com.example.remap.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.remap.ui.screens.MainScreen
import com.example.remap.ui.theme.RemapTheme
import com.yandex.mapkit.MapKitFactory
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RemapTheme {
                MainScreen()
            }
        }
    }
}