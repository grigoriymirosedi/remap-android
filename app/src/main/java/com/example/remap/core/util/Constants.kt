package com.example.remap.core.util

import java.time.format.DateTimeFormatter
import java.util.Locale

object Constants {
    //for emulator should use http://10.0.2.2:8080
    //for real android devices should use http://192.168.194.2:8080
    val BASE_URL = "https://remap-vfen.onrender.com/"

    val formatterRU = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale("ru"))

}