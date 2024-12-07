package com.example.core.util

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.core.content.ContextCompat
import com.example.core.models.CategoryType
import com.yandex.mapkit.geometry.Point

fun Context.getBitmapFromVectorDrawable(drawableId: Int): Bitmap? {
    val drawable = ContextCompat.getDrawable(this, drawableId) ?: return null

    val bitmap = Bitmap.createBitmap(
        drawable.intrinsicWidth,
        drawable.intrinsicHeight,
        Bitmap.Config.ARGB_8888) ?: return null
    val canvas = Canvas(bitmap)
    drawable.setBounds(0, 0, canvas.width, canvas.height)
    drawable.draw(canvas)

    return bitmap
}

fun Context.copyToClipboard(text: CharSequence) {
    val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip = ClipData.newPlainText("label",text)
    clipboard.setPrimaryClip(clip)
}

fun Point.toStringLatLngFormat(): String {
    return "${this.latitude} ${this.longitude}"
}

fun String.toCoordinateFormat(): String {
    return this.take(8)
}

fun String.toRightArgumentFormat(): String {
    return this.replace("/", "@")
}

fun String.toCategoryType(): String {
    return when(this) {
        "0" -> CategoryType.PAPER.text
        "1" -> CategoryType.PLASTIC.text
        "2" -> CategoryType.GLASS.text
        "3" -> CategoryType.METAL.text
        "4" -> CategoryType.TETRA_PACK.text
        "5" -> CategoryType.CLOTHES.text
        "6" -> CategoryType.LAMPS.text
        "7" -> CategoryType.CAPS.text
        "8" -> CategoryType.TECH.text
        "9" -> CategoryType.BATTERIES.text
        "10" -> CategoryType.TIRES.text
        "11" -> CategoryType.DANGEROUS.text
        else -> CategoryType.OTHER.text
    }
}

fun String.toPlacemarkAddressFormat(): String {
    return this.replace("@", "/")
}