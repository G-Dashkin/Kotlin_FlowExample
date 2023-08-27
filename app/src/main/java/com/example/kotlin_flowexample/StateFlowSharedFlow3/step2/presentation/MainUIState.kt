package com.example.kotlin_flowexample.StateFlowSharedFlow3.step2.presentation

sealed class MainUIState {
    object Loading : MainUIState()
    data class Success(val numSubscribers: Int)  : MainUIState()
    data class Error(val msg: String)  : MainUIState()
}
