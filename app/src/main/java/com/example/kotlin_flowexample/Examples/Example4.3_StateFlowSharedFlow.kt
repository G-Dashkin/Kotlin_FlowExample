package com.example.kotlin_flowexample

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

private val mutableSharedFlow = MutableSharedFlow<String>(replay = 1)
private val sharedFlow : SharedFlow<String> = mutableSharedFlow

suspend fun main() {
    // Кейс 3 эмитится только b, так как указано replay = 1 в MutableSharedFlow()

    getData()

    GlobalScope.launch {
        sharedFlow.collect() {
            println(it)
        }
    }

    delay(5000)

}

private suspend fun getData() {
    mutableSharedFlow.emit("a")
    delay(2000)
    mutableSharedFlow.emit("b")
}