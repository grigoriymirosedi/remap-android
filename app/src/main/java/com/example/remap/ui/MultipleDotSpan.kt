package com.example.remap.ui

import android.graphics.Canvas
import android.graphics.Paint
import android.text.style.LineBackgroundSpan
import com.prolificinteractive.materialcalendarview.spans.DotSpan

class MultipleDotSpan(val color: Array<Int>, val radius: Float = 7.5f, val spanType: Int) :
    DotSpan(radius, color[0]) {
    override fun drawBackground(
        canvas: Canvas, paint: Paint, left: Int, right: Int,
        top: Int, baseline: Int, bottom: Int, charSequence: CharSequence, start: Int, end: Int,
        lineNum: Int,
    ) {
        val oldColor = paint.getColor();
        if (color.isNotEmpty()) {
            paint.color = color[0];
        }

        val x: Float = (left + right) / 2.0f

        if (spanType == 1) { // We have 1 event on a single date
            canvas.drawCircle(x, bottom + radius, radius, paint)
        } else if (spanType == 2) { // We have 2 events on a single date
            val distanceBetweenCircles = radius * 3
            val startX = x - distanceBetweenCircles / 2
            val endX = x + distanceBetweenCircles / 2

            canvas.drawCircle(startX, bottom + radius, radius, paint)

            paint.color = color[1]
            canvas.drawCircle(endX, bottom + radius, radius, paint)
        } else if (spanType >= 3) {// We have 3+ events on a single date
            val distanceBetweenCircles = radius * 3
            val startX = x - distanceBetweenCircles
            val endX = x + distanceBetweenCircles

            canvas.drawCircle(startX, bottom + radius, radius, paint)

            paint.color = color[1]
            canvas.drawCircle(x, bottom + radius, radius, paint)

            val halfWidth = radius * 1.7f
            val halfHeight = radius / 2.7f

            paint.color = color[2]
            // Horizontal rect line
            canvas.drawRect(endX - halfWidth + distanceBetweenCircles, bottom + radius - halfHeight,
                endX + halfWidth - distanceBetweenCircles, bottom + radius + halfHeight, paint)

            // Vertical rect line
            canvas.drawRect(endX - halfHeight, bottom + radius - halfWidth +distanceBetweenCircles,
                endX + halfHeight, bottom + radius + halfWidth - distanceBetweenCircles, paint)
        }
        paint.color = oldColor
    }
}