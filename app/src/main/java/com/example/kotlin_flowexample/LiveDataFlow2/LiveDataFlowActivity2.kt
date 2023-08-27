package com.example.kotlin_flowexample.LiveDataFlow2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.kotlin_flowexample.LiveDataFlow1.MainViewModel
import com.example.kotlin_flowexample.databinding.ActivityLiveDataFlow1Binding
import com.example.kotlin_flowexample.databinding.ActivityLiveDataFlow2Binding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class LiveDataFlowActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityLiveDataFlow2Binding
    private lateinit var timerViewModel: TimerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLiveDataFlow2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        timerViewModel = ViewModelProvider(this)[TimerViewModel::class.java]
        timerViewModel.startTimerList()
        timerViewModel.startTimerFlow()

        timerViewModel.timerLiveData.observe(this) {
            binding.txtViewLiveData.text = it.toString()
            Log.d("MyLog", "LiveData: $it")
        }

        lifecycleScope.launch {
            timerViewModel.timerStateFlow.collect{
                binding.txtViewStateFlow.text = it.toString()
                Log.d("MyLog", "StateFlow: $it")
            }
        }
    }
}