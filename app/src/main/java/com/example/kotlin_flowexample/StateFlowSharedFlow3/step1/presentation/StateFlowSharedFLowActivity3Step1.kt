package com.example.kotlin_flowexample.StateFlowSharedFlow3.step1.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin_flowexample.databinding.ActivityStateFlowSharedFlow3Step1Binding

class StateFlowSharedFLowActivity3Step1 : AppCompatActivity() {

    // Запускаем прилоежине, смотрим в лог как работает StateFlow

    private lateinit var binding: ActivityStateFlowSharedFlow3Step1Binding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStateFlowSharedFlow3Step1Binding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        setContentView(binding.root)

        viewModel.example()

    }
}