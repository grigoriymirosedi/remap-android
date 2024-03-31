package com.example.remap.ui.screens.calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.remap.data.remote.dto.RecyclePointDTO
import com.example.remap.domain.models.RecyclePoint
import com.example.remap.domain.repository.RecyclePointRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val repository: RecyclePointRepository,
) : ViewModel() {

//    private val _recyclePoints = MutableStateFlow(listOf<RecyclePoint>())
//    val recyclePoints: StateFlow<List<RecyclePoint>> = _recyclePoints
//
//    private fun initRecyclePoint() {
//        viewModelScope.launch(Dispatchers.IO) {
//            runCatching {
//                repository.getRecyclePoints()
//            }.onSuccess {
//                val handleResponse = it.body()?.map { it.toRecyclePoint() } ?: emptyList()
//                _recyclePoints.emit(value = handleResponse)
//            }.onFailure {
//                _recyclePoints.emit(value = emptyList())
//            }
//        }
//    }
}