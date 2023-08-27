package com.example.kotlin_flowexample.StateFlowSharedFlow3.step1.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin_flowexample.StateFlowSharedFlow3.step1.data.SuscribeteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    val suscribeteRepository = SuscribeteRepository()

    fun example() {
        viewModelScope.launch {
            suscribeteRepository.counter.collect() {
                Log.d("MyLog", it.toString())

            }
        }
    }

}