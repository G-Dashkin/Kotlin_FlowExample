package com.example.kotlin_flowexample.StateFlowSharedFlow2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.kotlin_flowexample.databinding.ActivityStateFlowSharedFlow2Binding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class StateFlowSharedFLowActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityStateFlowSharedFlow2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStateFlowSharedFlow2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {

            // Shared Flow 1
            btnSharedFlowExample1.setOnClickListener {
                GlobalScope.launch(Dispatchers.Main) {
                    val result = producerSharedFlowOne()
                    result.collect {
                        Log.d("MyLog", "Collecting: $it")
                    }
                }
            }

            // Shared Flow 2 delay(2500)
            btnSharedFlowExample2.setOnClickListener {
                GlobalScope.launch(Dispatchers.Main) {
                    val result = producerSharedFlowOne()
                    delay(2500)
                    result.collect {
                        Log.d("MyLog", "Collecting: $it")
                    }
                }
            }

            // Shared Flow 3 delay(6000)
            btnSharedFlowExample3.setOnClickListener {
                GlobalScope.launch(Dispatchers.Main) {
                    val result = producerSharedFlowOne()
                    delay(6000)
                    result.collect {
                        Log.d("MyLog", "Collecting: $it")
                    }
                }
            }

            // State Flow 4
            btnStateFlowExample4.setOnClickListener {
                GlobalScope.launch(Dispatchers.Main) {
                    val result = producerStateFlowTwo()
                    result.collect {
                        Log.d("MyLog", "Collecting: $it")
                    }
                }
            }

            // State Flow 5 delay(6000)
            btnStateFlowExample5.setOnClickListener {
                GlobalScope.launch(Dispatchers.Main) {
                    val result = producerStateFlowTwo()
                    delay(6000)
                    result.collect {
                        Log.d("MyLog", "Collecting: $it")
                    }
                }
            }

            // State Flow 6 StateFlow return No .collect()
            btnStateFlowExample6.setOnClickListener {
                GlobalScope.launch(Dispatchers.Main) {
                    val result = producerStateFlowThree()
                    Log.d("MyLog", "Result: ${result.value}")
                }
            }

        }

    }
}

private fun producerSharedFlowOne(): Flow<Int> {
    val mutableSharedFlow = MutableSharedFlow<Int>()
    GlobalScope.launch {
        val list = listOf<Int>(1,2,3,4,5)
        list.forEach {
            mutableSharedFlow.emit(it)
            Log.d("MyLog", "Emitting: $it")
            delay(1000)
        }
    }
    return mutableSharedFlow
}

private fun producerStateFlowTwo(): Flow<Int> {
    val mutableSharedFlow = MutableStateFlow(10)
    GlobalScope.launch {
        delay(2000)
        mutableSharedFlow.emit(20)
        delay(2000)
        mutableSharedFlow.emit(30)
    }
    return mutableSharedFlow
}

private fun producerStateFlowThree(): StateFlow<Int> {
    val mutableSharedFlow = MutableStateFlow(10)
    GlobalScope.launch {
        delay(2000)
        mutableSharedFlow.emit(20)
        delay(2000)
        mutableSharedFlow.emit(30)
    }
    return mutableSharedFlow
}