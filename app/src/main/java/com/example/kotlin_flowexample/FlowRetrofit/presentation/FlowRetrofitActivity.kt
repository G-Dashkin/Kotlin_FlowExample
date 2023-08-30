package com.example.kotlin_flowexample.FlowRetrofit.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.kotlin_flowexample.R
import com.example.kotlin_flowexample.databinding.ActivityFlowRetrofitBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FlowRetrofitActivity : AppCompatActivity() {

    private var _binding : ActivityFlowRetrofitBinding? = null
    private val binding get() = _binding!!

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityFlowRetrofitBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = findNavController(R.id.fragmentContainerView)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp() || navController.navigateUp()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}











