package com.example.kotlin_flowexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlin_flowexample.FlowOperators.FlowOperatorsActivity
import com.example.kotlin_flowexample.FlowRetrofit.presentation.FlowRetrofitActivity
import com.example.kotlin_flowexample.FlowRoom.presentation.FlowRoomActivity
import com.example.kotlin_flowexample.LiveDataFlow1.LiveDataFlowActivity1
import com.example.kotlin_flowexample.LiveDataFlow2.LiveDataFlowActivity2
import com.example.kotlin_flowexample.LiveDataFlow3.LiveDataFlowActivity3
import com.example.kotlin_flowexample.LiveDataFlow4.LiveDataFlowActivity4
import com.example.kotlin_flowexample.LiveDataFlow5.LiveDataFlowActivity5
import com.example.kotlin_flowexample.StateFlowSharedFlow1.StateFlowSharedFLowActivity1
import com.example.kotlin_flowexample.StateFlowSharedFlow2.StateFlowSharedFLowActivity2
import com.example.kotlin_flowexample.StateFlowSharedFlow3.StateFlowSharedFLowActivity3
import com.example.kotlin_flowexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnLiveDataFlowExample1.setOnClickListener {
                startActivity(Intent(this@MainActivity, LiveDataFlowActivity1::class.java))
            }
            btnLiveDataFlowExample2.setOnClickListener {
                startActivity(Intent(this@MainActivity, LiveDataFlowActivity2::class.java))
            }
            btnLiveDataFlowExample3.setOnClickListener {
                startActivity(Intent(this@MainActivity, LiveDataFlowActivity3::class.java))
            }
            btnLiveDataFlowExample4.setOnClickListener {
                startActivity(Intent(this@MainActivity, LiveDataFlowActivity4::class.java))
            }
            btnLiveDataFlowExample5.setOnClickListener {
                startActivity(Intent(this@MainActivity, LiveDataFlowActivity5::class.java))
            }
            btnLiveDataFlowExample5.setOnClickListener {
                startActivity(Intent(this@MainActivity, LiveDataFlowActivity5::class.java))
            }
            btnStateFlowShared1.setOnClickListener {
                startActivity(Intent(this@MainActivity, StateFlowSharedFLowActivity1::class.java))
            }
            btnStateFlowShared2.setOnClickListener {
                startActivity(Intent(this@MainActivity, StateFlowSharedFLowActivity2::class.java))
            }
            btnStateFlowShared3.setOnClickListener {
                startActivity(Intent(this@MainActivity, StateFlowSharedFLowActivity3::class.java))
            }
            btnFlowOperators.setOnClickListener {
                startActivity(Intent(this@MainActivity, FlowOperatorsActivity::class.java))
            }
            btnFlowRoom.setOnClickListener {
                startActivity(Intent(this@MainActivity, FlowRoomActivity::class.java))
            }

            btnFlowRetrofit.setOnClickListener {
                startActivity(Intent(this@MainActivity, FlowRetrofitActivity::class.java))
            }

        }

    }
}