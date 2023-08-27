package com.example.kotlin_flowexample.LiveDataFlow4

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow

class NewViewModelOne:ViewModel() {

    private val _liveData = MutableLiveData<String>("Live Data Info")
    val liveData = _liveData as LiveData<String>

    fun updateLiveData(value: String) {
        _liveData.value = value
    }

    fun getFlowInfo(): Flow<String> {
        return flow {
            repeat(5) {
                emit("Flow $it")
                delay(1000)
            }
        }
    }
}