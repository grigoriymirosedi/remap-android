package com.example.home.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.remote.dto.toRecyclePoint
import com.example.core.models.RecyclePoint
import com.example.data.repository.RecyclePointRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: RecyclePointRepository
): ViewModel() {

    private val _isLoading = mutableStateOf(true)
    val isLoading = _isLoading

    private val _recyclePoints = MutableStateFlow<List<RecyclePoint>>(emptyList())
    var recyclePoints: StateFlow<List<RecyclePoint>> = _recyclePoints

    init {
        initRecyclePoint()
    }

    private fun initRecyclePoint() {
        viewModelScope.launch(Dispatchers.IO) {
//            runCatching {
//                repository.getRecyclePoints(categoryType = null)
//            }.onSuccess {
//                val handleResponse = it.body()?.map { it.toRecyclePoint() } ?: emptyList()
//                _recyclePoints.addAll(handleResponse)
//                _isLoading.value = false
//            }.onFailure {
//                _recyclePoints.addAll(emptyList())
//            }
            _recyclePoints.value = emptyList()
        }
    }
}