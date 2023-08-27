package com.example.kotlin_flowexample.LiveDataFlow5

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow

class NewViewModelTwo: ViewModel() {

    private val _liveData = MutableLiveData<String>("Live Data Info")
    val liveData = _liveData as LiveData<String>
    fun updateLiveData(value: String) {
        _liveData.value = value
    }

    private val _stateFlow = MutableStateFlow<String>("State Flow Info")
    val stateFlow = _stateFlow as StateFlow<String>
    fun updateStateFlowMutableStateFlow(value: String) {
        _stateFlow.value = value
    }

    private val _stateFlowEmit = MutableStateFlow("State Flow Emit Info")
    val stateFlowEmit = _stateFlowEmit as StateFlow<String>

    fun updateStateFlowEmit(value: String) {
        _stateFlowEmit.value = value
    }
}