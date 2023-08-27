package com.example.kotlin_flowexample.LiveDataFlow5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.kotlin_flowexample.databinding.ActivityLiveDataFlow5Binding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class LiveDataFlowActivity5 : AppCompatActivity() {

    private lateinit var binding: ActivityLiveDataFlow5Binding
    private lateinit var viewModel: NewViewModelTwo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLiveDataFlow5Binding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[NewViewModelTwo::class.java]

        binding.btnLiveData.setOnClickListener {
            viewModel.updateLiveData(binding.txtLiveStateValue.text.toString())
        }

        binding.btnStateFLow.setOnClickListener {
            viewModel.updateStateFlowMutableStateFlow(binding.txtLiveStateValue.text.toString())
        }

        binding.btnStateFLowEmit.setOnClickListener {
            viewModel.updateStateFlowEmit(binding.txtLiveStateValue.text.toString())
        }

        viewModel.liveData.observe(this) {
            binding.txtLiveData.text = it
//            Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
        }

        lifecycleScope.launch {
            viewModel.stateFlow.collect() {
                binding.txtStateFLow.text = it
//                Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
            }
        }

        lifecycleScope.launch {
            viewModel.stateFlowEmit.collect(){
                binding.txtStateFLowEmit.text = it
//                Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
            }
        }


    }
}