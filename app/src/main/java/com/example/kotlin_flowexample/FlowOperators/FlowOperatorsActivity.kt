package com.example.kotlin_flowexample.FlowOperators

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.kotlin_flowexample.databinding.ActivityFlowOperatorsBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class FlowOperatorsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFlowOperatorsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFlowOperatorsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSimpleFlow.setOnClickListener {
            lifecycleScope.launch {
                simpleFlow().collect { value -> Log.d("MyLog", "onCreate: $value") }
            }
        }

        binding.btnFlowForEach.setOnClickListener {
            lifecycleScope.launch {
                flow {
                    (0..10).forEach {
                        emit(it)
                    }
                }.collect{
                    Log.d("MyLog", "flow forEach{}: $it")
                }
            }
        }

        binding.btnFlowOfInt.setOnClickListener {
            lifecycleScope.launch {
                flowOf(4,2,5,6,3).collect {
                    Log.d("MyLog", "flowOf() Int: $it")
                }
            }
        }

        binding.btnFlowOfString.setOnClickListener {
            lifecycleScope.launch {
                flowOf("Mike", "Tome", "Bill", "Need").collect {
                    Log.d("MyLog", "flowOf() String: $it")
                }
            }
        }

        binding.btnAsFlow.setOnClickListener {
            lifecycleScope.launch {
                (1..5).asFlow().collect {
                    Log.d("MyLog", "asFlow(): $it")
                }
            }
        }

        binding.btnChannelFlow.setOnClickListener {
            lifecycleScope.launch {
                channelFlow {
                    (0..10).forEach {
                        send(it)
                    }
                }.collect{
                    Log.d("MyLog", "channelFlow: $it")
                }
            }
        }

        binding.btnFilterMap.setOnClickListener {
            lifecycleScope.launch {
                (1..5).asFlow()
                    .filter {
                        it % 2 == 0
                    }
                    .map {
                        it * it
                    }
                    .collect {
                        Log.d("MyLog", "filter{} & .map {}: $it")
                    }
            }
        }

        binding.btnFirst.setOnClickListener {
            lifecycleScope.launch {
                val item = (13..20).asFlow().first { it % 2 == 0 }
                Log.d("MyLog", "first{} : $item")
            }
        }

        binding.btnLast.setOnClickListener {
            lifecycleScope.launch {
                val item = (20..30).asFlow().last()
                Log.d("MyLog", "last() : $item")
            }
        }

        binding.btnSet.setOnClickListener {
            val flowNumbers = flow {
                delay(100)
                emit(1)
                delay(100)
                emit(2)
            }
            lifecycleScope.launch {
                val item = flowNumbers.toSet()
                Log.d("MyLog", "toSet() : $item")
            }
        }

        binding.btnList.setOnClickListener {
            val flowNumbers = flow {
                delay(100)
                emit(1)
                delay(100)
                emit(2)
            }
            lifecycleScope.launch {
                val item = flowNumbers.toList()
                Log.d("MyLog", "toList() : $item")
            }
        }

        binding.btnReduce.setOnClickListener {
            lifecycleScope.launch {
                val sum = (1..5).asFlow()
                    .reduce{ a, b -> a + b } // 1+2+4+5
                Log.d("MyLog", "reduce() : $sum")
            }
        }

        binding.btnFold.setOnClickListener {
            lifecycleScope.launch {
                val sum = (1..5).asFlow()
                    .fold(10){ a, b -> a + b } // 1+2+4+5 + 10
                Log.d("MyLog", "fold() : $sum")
            }
        }

        binding.btnMap.setOnClickListener {
            val nameFlow = listOf("Alex", "Rey", "Emd", "Frank").asFlow()

            lifecycleScope.launch {
                nameFlow.map {
                    it.length
                }.collect {
                    Log.d("MyLog", "map() : $it")
                }
            }
        }

        binding.btnFilter.setOnClickListener {
            val nameFlow = listOf("Alex", "Rey", "Emd", "Frank").asFlow()

            lifecycleScope.launch {
                nameFlow
                    .filter {it == "Rey" }
                    .collect {
                    Log.d("MyLog", "filter() : $it")
                }
            }
        }

        binding.btnTake.setOnClickListener {

            val nameFlow = listOf("Alex", "Rey", "Emd", "Frank").asFlow()

            lifecycleScope.launch {
                nameFlow
                    .take(2)
                    .collect {
                        Log.d("MyLog", "take() : $it")
                    }
            }

        }

        binding.btnZip.setOnClickListener {

            val flowDigits = flowOf(1,2,3).onEach { delay(10) }
            val flowString = flowOf("a","b","c").onEach { delay(15) }

            lifecycleScope.launch {
                flowDigits.zip(flowString) {
                    i, s ->
                    i.toString() + s
                }.collect {
                    Log.d("MyLog", "zip() : $it")
                }
            }
        }

        binding.btnDropWhile.setOnClickListener {
            lifecycleScope.launch {
                flowOf(1,2,3,4,5)
                    .dropWhile { it < 2 }
                    .collect{
                        Log.d("MyLog", "dropWhile() : $it")
                    }
            }
        }

        binding.btnTransform.setOnClickListener {
            lifecycleScope.launch {
                flowOf(1,2,3,4,5,1)
                    .transform {
                        emit(it)
                        emit(it * 10)
                    }
                    .collect{
                        Log.d("MyLog", "transform() : $it")
                    }
            }
        }

        binding.btnWithIndex.setOnClickListener {
            val nameFlow = listOf("Alex", "Rey", "Emd", "Frank").asFlow()
            lifecycleScope.launch {
                nameFlow
                    .withIndex()
                    .collect {
                        Log.d("MyLog", "withIndex() : $it")
                    }
            }
        }

        binding.btnFlowOn.setOnClickListener {
            lifecycleScope.launch {
                floOnSample().collect {
                    Log.d("MyLog", "floOn Collect: $it")
                }
            }
        }

        binding.btnException.setOnClickListener {
            lifecycleScope.launch {
                (1..10).asFlow()
                    .map {
                        check(it != 3)  {"Value $it"}
                    }
                    .onCompletion {
                        Log.d("MyLog", "onCompletion")
                    }
                    .catch {
                        Log.d("MyLog", "Catch: $it")
                    }
                    .collect{
                        Log.d("MyLog", "$it")
                    }
            }
        }



    }

    private fun simpleFlow(): Flow<Int> = flow {
        for (i in 1..3) {
            delay(100)
            emit(i)
        }
    }

    private fun floOnSample():Flow<Int> = flow {
        for(i in 1..3) {
            delay(100)
            Log.d("MyLog", "Emitting() : $i")
            emit(i)
        }
    }.flowOn(Dispatchers.Default)
}