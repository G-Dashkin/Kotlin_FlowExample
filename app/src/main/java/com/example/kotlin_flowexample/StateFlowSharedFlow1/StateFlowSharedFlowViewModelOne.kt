package com.example.kotlin_flowexample.StateFlowSharedFlow1

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class StateFlowSharedFlowViewModelOne: ViewModel() {

    private val _stateFlow = MutableStateFlow("State Flow Value")
    val stateFlow = _stateFlow as StateFlow<String>
    fun updateStateFlow(value:String) {
        _stateFlow.value = value
    }

    private val _sharedFlow = MutableSharedFlow<String>()
    val sharedFlow = _sharedFlow as SharedFlow<String>
    fun updateSharedFlow(value:String) {
        viewModelScope.launch {
            _sharedFlow.emit(value)
        }
    }


}