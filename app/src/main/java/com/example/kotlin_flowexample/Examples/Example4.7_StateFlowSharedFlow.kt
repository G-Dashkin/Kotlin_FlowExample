package com.example.kotlin_flowexample

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

private val mutableSharedFlow = MutableSharedFlow<PersonThree>()
private val sharedFlow : SharedFlow<PersonThree> = mutableSharedFlow

suspend fun main() {
    // Кейс 7 убираем replay = 2 из MutableSharedFlow()
    // Все равно выводятся только 2 занчения

    getData()

    GlobalScope.launch {
        getFlow().collect() {
            println("1st $it")
        }
    }

    GlobalScope.launch {
        getFlow().collect() {
            println("2nd $it")
        }
    }

    delay(5000)

}

private fun getFlow() = flow<PersonThree> {
    delay(2000)
    emit(PersonThree())
}

private suspend fun getData() {
    mutableSharedFlow.emit(PersonThree())
    delay(1000)
    mutableSharedFlow.emit(PersonThree())
}

private class PersonThree