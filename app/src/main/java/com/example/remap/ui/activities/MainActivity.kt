package com.example.remap.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.remap.ui.screens.MainScreen
import com.example.remap.ui.theme.RemapTheme

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