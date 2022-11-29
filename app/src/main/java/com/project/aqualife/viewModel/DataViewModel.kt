package com.project.aqualife.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DataViewModel : ViewModel() {
    private val _recentIndex = MutableLiveData<Int>()
    val recentIndex get() = _recentIndex

    fun updateIndexValue(value : Int){
        recentIndex.postValue(value)
    }
}