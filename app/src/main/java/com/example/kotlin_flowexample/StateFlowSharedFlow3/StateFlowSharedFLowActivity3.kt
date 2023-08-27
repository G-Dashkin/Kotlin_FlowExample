package com.example.kotlin_flowexample.StateFlowSharedFlow3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlin_flowexample.StateFlowSharedFlow3.step1.presentation.StateFlowSharedFLowActivity3Step1
import com.example.kotlin_flowexample.StateFlowSharedFlow3.step2.presentation.StateFlowSharedFLowActivity3Step2
import com.example.kotlin_flowexample.databinding.ActivityStateFlowSharedFlow3Binding

class StateFlowSharedFLowActivity3 : AppCompatActivity() {

    private lateinit var binding: ActivityStateFlowSharedFlow3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStateFlowSharedFlow3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnStep1.setOnClickListener {
                startActivity(Intent(this@StateFlowSharedFLowActivity3, StateFlowSharedFLowActivity3Step1::class.java))
            }
            btnStep2.setOnClickListener {
                startActivity(Intent(this@StateFlowSharedFLowActivity3, StateFlowSharedFLowActivity3Step2::class.java))
            }
        }
    }
}