package com.example.kotlin_flowexample.LiveDataFlow2

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class TimerViewModel: ViewModel() {

    // LiveData
    private val _timerLiveData = MutableLiveData<Int>()
    val timerLiveData: MutableLiveData<Int> = _timerLiveData

    // StateFlow
    private val _timerStateFlow = MutableStateFlow(0)
    val timerStateFlow: MutableStateFlow<Int> = _timerStateFlow

    // Вариант 1
    fun startTimer() {
        viewModelScope.launch {
            for (i in 1..10) {
                _timerLiveData.value = i
                delay(1000)
            }
        }
    }

    // Вариант 2
    fun startTimerList() {
        viewModelScope.launch {
            val list = listOf<Int>(1,2,3,4,5,6,7,8,9,10)
            for (i in list) {
                _timerLiveData.value = i
                delay(1000)
            }
        }
    }

    // Вариант 3 Flow
    fun startTimerFlow() {
        viewModelScope.launch {
            val list = listOf<Int>(1,2,3,4,5,6,7,8,9,10)
            for (i in list) {
                _timerStateFlow.emit(i)
                delay(1000)
            }
        }
    }
}