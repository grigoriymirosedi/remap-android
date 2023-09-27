package com.example.remap.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.remap.domain.repository.RecyclePointRepository

class ViewModelProviderFactory(private val recyclePointRepository: RecyclePointRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MapFragmentViewModel(recyclePointRepository) as T
    }
}