package com.example.kotlin_flowexample.StateFlowSharedFlow3.step2.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin_flowexample.StateFlowSharedFlow3.step1.data.SuscribeteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    val suscribeteRepository = SuscribeteRepository()

    private val _uiState = MutableStateFlow<MainUIState>(MainUIState.Loading)
    val uiState: StateFlow<MainUIState> = _uiState

    fun example() {
        viewModelScope.launch {
            suscribeteRepository.counter
                .map { it.toString() }
                .collect() {
                Log.d("MyLog", it)
            }
        }
    }

    fun example2() {
        viewModelScope.launch {
            suscribeteRepository.counter
                .map { it.toString() }
                .onEach { save(it) }
                .catch {
                    Log.d("MyLog", "Error: ${it.message}")
                }
                .collect() {
                    Log.d("MyLog", it)
            }
        }
    }

    fun example3() {
        viewModelScope.launch {
            suscribeteRepository.counter
                .catch { _uiState.value = MainUIState.Error(it.message.orEmpty()) }
                .flowOn(Dispatchers.IO)
                .collect() {
                _uiState.value = MainUIState.Success(it)
            }
        }
    }

    private fun save(info: String) {

    }

}