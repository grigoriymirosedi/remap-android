package com.example.remap.ui

import android.graphics.Color
import android.util.Log
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.prolificinteractive.materialcalendarview.spans.DotSpan
import java.util.Calendar

class EventDecorator(
    val color: Int, private val radius: Float, private val dates: Map<CalendarDay, Int>, private val spanType: Int) : DayViewDecorator {

    private val colorValues = arrayOf(
        Color.parseColor("#98bff4"),
        Color.parseColor("#e7aec9"),
        Color.parseColor("#d3d3d3")
    )

    override fun shouldDecorate(day: CalendarDay?): Boolean {
        return dates.contains(day)
    }

    override fun decorate(view: DayViewFacade?) {
        val span = MultipleDotSpan(colorValues, radius, spanType)
        view?.addSpan(span)
    }

}