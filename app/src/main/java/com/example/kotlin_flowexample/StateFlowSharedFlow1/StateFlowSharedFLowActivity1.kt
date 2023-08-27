package com.example.kotlin_flowexample.StateFlowSharedFlow1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.kotlin_flowexample.databinding.ActivityStateFlowSharedFlow1Binding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class StateFlowSharedFLowActivity1 : AppCompatActivity() {

    private lateinit var binding: ActivityStateFlowSharedFlow1Binding
    private lateinit var viewModel: StateFlowSharedFlowViewModelOne

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStateFlowSharedFlow1Binding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[StateFlowSharedFlowViewModelOne::class.java]
        setContentView(binding.root)

        binding.btnStateFLow.setOnClickListener {
            viewModel.updateStateFlow(binding.txtStateValue.text.toString())
        }

        binding.btnSharedFlow.setOnClickListener {
            viewModel.updateSharedFlow(binding.txtStateValue.text.toString())
        }

        lifecycleScope.launch {
            viewModel.sharedFlow.collect() {
                binding.txtSharedFlow.text = it
                Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
            }
        }
        lifecycleScope.launch {
            viewModel.stateFlow.collect() {
                binding.txtStateFlow.text = it
                Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()
            }
        }
    }
}