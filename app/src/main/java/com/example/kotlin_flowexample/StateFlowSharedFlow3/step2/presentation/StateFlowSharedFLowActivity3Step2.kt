package com.example.kotlin_flowexample.StateFlowSharedFlow3.step2.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.kotlin_flowexample.databinding.ActivityStateFlowSharedFlow3Step2Binding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class StateFlowSharedFLowActivity3Step2 : AppCompatActivity() {

    // пример функции map{} с StateFlow (во вьюМоделе)
    // + создание сиелд класса

    private lateinit var binding: ActivityStateFlowSharedFlow3Step2Binding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStateFlowSharedFlow3Step2Binding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        setContentView(binding.root)

//        viewModel.example()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect() {
                    when(it) {
                        is MainUIState.Error -> {
                            binding.progressBar.isVisible = false
                            Toast.makeText(this@StateFlowSharedFLowActivity3Step2, "Error: ${it.msg}", Toast.LENGTH_SHORT).show()
                        }
                        MainUIState.Loading -> { binding.progressBar.isVisible = true }
                        is MainUIState.Success -> {
                            binding.progressBar.isVisible = false
                            Toast.makeText(this@StateFlowSharedFLowActivity3Step2, "Success: ${it.numSubscribers}", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }

        }

        viewModel.example3()

    }
}