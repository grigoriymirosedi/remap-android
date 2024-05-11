package com.example.remap.ui.screens.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.remap.data.remote.dto.toRecyclePoint
import com.example.remap.domain.models.RecyclePoint
import com.example.remap.domain.repository.RecyclePointRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: RecyclePointRepository
): ViewModel() {

    private val _isLoading = mutableStateOf(true)
    val isLoading = _isLoading

    private val _recyclePoints = mutableStateListOf<RecyclePoint>()
    var recyclePoints: List<RecyclePoint> = _recyclePoints

    init {
        initRecyclePoint()
    }

    private fun initRecyclePoint() {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                repository.getRecyclePoints()
            }.onSuccess {
                val handleResponse = it.body()?.map { it.toRecyclePoint() } ?: emptyList()
                _recyclePoints.addAll(handleResponse)
                _isLoading.value = false
            }.onFailure {
                _recyclePoints.addAll(emptyList())
            }
        }
    }
}