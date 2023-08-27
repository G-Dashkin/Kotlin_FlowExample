package com.example.kotlin_flowexample.LiveDataFlow3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.kotlin_flowexample.databinding.ActivityLiveDataFlow3Binding
import kotlinx.coroutines.launch

class LiveDataFlowActivity3 : AppCompatActivity() {

    private lateinit var binding: ActivityLiveDataFlow3Binding
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var viewModelFactory: MainActivityViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLiveDataFlow3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModelFactory = MainActivityViewModelFactory(125)
        viewModel = ViewModelProvider(this,viewModelFactory).get(MainActivityViewModel::class.java)

//        viewModel.totalData.observe(this) {
//            binding.resultTextView.text = it.toString()
//        }

        lifecycleScope.launch {
            viewModel.flowTotal.collect{
                binding.resultTextView.text = it.toString()
            }
        }

        binding.insertButton.setOnClickListener {
            viewModel.setTotal(binding.inputEditText.text.toString().toInt())
        }

    }
}