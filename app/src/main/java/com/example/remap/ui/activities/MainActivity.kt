package com.example.remap.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.remap.R
import com.example.remap.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}