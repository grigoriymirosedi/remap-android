package com.example.remap.ui.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.remap.ui.EventDecorator
import com.example.remap.databinding.FragmentCalendarBinding
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView

class CalendarFragment : Fragment() {

    private lateinit var materialCalendarView: MaterialCalendarView

    private var _binding: FragmentCalendarBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCalendarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        materialCalendarView = binding.calendarView

        //Need to change it later with get response
        val dayInstanceMap: HashMap<CalendarDay, Int> = hashMapOf(
            CalendarDay.from(2024, 1, 28) to 4,
            CalendarDay.from(2024, 1 ,26) to 2,
            CalendarDay.from(2024, 1, 29) to 1,
            CalendarDay.from(2024, 1, 14) to 1,
            CalendarDay.from(2024, 1, 13) to 1,
            )

        val singleDotDates = dayInstanceMap.filter { it.value == 1 }
        val doubleDotDates = dayInstanceMap.filter { it.value == 2 }
        val tripleDotDates = dayInstanceMap.filter { it.value >= 3 }

        for(decorator in singleDotDates) {
            val currentDecorator = EventDecorator(Color.GREEN, 7.5f, singleDotDates, decorator.value)
            materialCalendarView.addDecorator(currentDecorator)
        }

        for(decorator in doubleDotDates) {
            val currentDecorator = EventDecorator(Color.GREEN, 7.5f, doubleDotDates, decorator.value)
            materialCalendarView.addDecorator(currentDecorator)
        }

        for(decorator in tripleDotDates) {
            val currentDecorator = EventDecorator(Color.GREEN, 7.5f, tripleDotDates, decorator.value)
            materialCalendarView.addDecorator(currentDecorator)
        }
    }

}