package com.example.kotlin_flowexample

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

private val mutableSharedFlow = MutableSharedFlow<String>(replay = 2)
private val sharedFlow : SharedFlow<String> = mutableSharedFlow

suspend fun main() {
    // Кейс 4 меняем replay = 1 на 2  и у нас выводится a и b

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
    delay(1000)
    mutableSharedFlow.emit("b")
}