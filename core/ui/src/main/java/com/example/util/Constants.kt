package com.example.util

import java.time.format.DateTimeFormatter
import java.util.Locale


const val INIT_LATITUDE: Double = 47.222109
const val INIT_LONGITUDE: Double = 39.718813
const val DEFAULT_ZOOM: Float = 15f
const val DEFAULT_AZIMUTH: Float = 0f
const val DEFAULT_TILT: Float = 0f

object Constants {
    //for emulator should use http://10.0.2.2:8080
    //for real android devices should use http://192.168.0.8:8080
    val BASE_URL = "http://192.168.0.108:80/"//"https://remap-vfen.onrender.com/"

    val formatterRU = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale("ru"))

}