package com.example.kotlin_flowexample

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

private val mutableSharedFlow = MutableSharedFlow<String>()
private val sharedFlow : SharedFlow<String> = mutableSharedFlow

suspend fun main() {
    // Кейс 2 вывод в лог

    GlobalScope.launch {
        sharedFlow.collect() {
            println(it)
        }
    }

    getData()

    delay(5000)

}

private suspend fun getData() {
    delay(2000)
    mutableSharedFlow.emit("b")
}