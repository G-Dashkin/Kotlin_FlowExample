package com.example.kotlin_flowexample.LiveDataFlow1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.kotlin_flowexample.databinding.ActivityLiveDataFlow1Binding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class LiveDataFlowActivity1 : AppCompatActivity() {

    private lateinit var binding: ActivityLiveDataFlow1Binding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLiveDataFlow1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        binding.btnLiveData.setOnClickListener {
            mainViewModel.triggerLiveData()
        }

        binding.btnStateFlow.setOnClickListener {
            mainViewModel.triggerStateFlow()
        }

        binding.btnFlow.setOnClickListener {
            lifecycleScope.launch {
                mainViewModel.triggerFlow().collectLatest {
                    binding.tvFlow.text = it.toString()
                }
            }
        }

        binding.btnSharedFlow.setOnClickListener {
            mainViewModel.triggerSharedFlow()
        }
        subscribeToObservables()
    }

    private fun subscribeToObservables() {
        mainViewModel.liveData.observe(this) {
            binding.tvLiveData.text = it
        }
        lifecycleScope.launch {
            mainViewModel.stateFlow.collectLatest {
                binding.tvStateFlow.text = it
//                Snackbar.make(
//                    binding.root,
//                    it,
//                    Snackbar.LENGTH_LONG
//                ).show()
            }

            mainViewModel.sharedFlow.collectLatest {
                Snackbar.make(
                    binding.root,
                    it,
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }
}