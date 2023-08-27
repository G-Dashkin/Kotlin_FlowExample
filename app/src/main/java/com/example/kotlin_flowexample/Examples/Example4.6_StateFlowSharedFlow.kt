package com.example.kotlin_flowexample

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

private val mutableSharedFlow = MutableSharedFlow<PersonTwo>(replay = 2)
private val sharedFlow : SharedFlow<PersonTwo> = mutableSharedFlow

suspend fun main() {
    // Кейс 6 создаем отдельную функцию getFlow() и внутри flow
    // Вместо 4-х значений, в лог выводятся только 2

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

private fun getFlow() = flow<PersonTwo> {
    delay(2000)
    emit(PersonTwo())
}

private suspend fun getData() {
    mutableSharedFlow.emit(PersonTwo())
    delay(1000)
    mutableSharedFlow.emit(PersonTwo())
}

private class PersonTwo