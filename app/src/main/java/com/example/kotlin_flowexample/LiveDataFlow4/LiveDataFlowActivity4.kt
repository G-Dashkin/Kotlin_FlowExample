package com.example.kotlin_flowexample.LiveDataFlow4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.kotlin_flowexample.databinding.ActivityLiveDataFlow4Binding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class LiveDataFlowActivity4 : AppCompatActivity() {

    private lateinit var binding: ActivityLiveDataFlow4Binding
    private lateinit var viewModel: NewViewModelOne

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLiveDataFlow4Binding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[NewViewModelOne::class.java]

        binding.apply {

            btnChangeLiveData.setOnClickListener {
                viewModel.updateLiveData(txtChangeValue.text.toString())
            }

            btnChangeFlow.setOnClickListener {

                lifecycleScope.launch {
                    viewModel.getFlowInfo().collect() {
                        binding.txtFLowData.text = it
                    }
                }
            }

            viewModel.liveData.observe(this@LiveDataFlowActivity4) {
                binding.txtLiveData.text = it
            }
        }
    }
}