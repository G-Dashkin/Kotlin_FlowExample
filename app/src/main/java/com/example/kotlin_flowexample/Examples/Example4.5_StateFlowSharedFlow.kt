package com.example.kotlin_flowexample

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

private val mutableSharedFlow = MutableSharedFlow<PersonOne>(replay = 2)
private val sharedFlow : SharedFlow<PersonOne> = mutableSharedFlow

suspend fun main() {
    // Кейс 5 создаем отдельный класс PersonOne и параметризируем им SharedFlow
    // вызываем метод .collect() два раза. Смотрим id кассов Person

    getData()

    GlobalScope.launch {
        sharedFlow.collect() {
            println("1st $it")
        }
    }

    GlobalScope.launch {
        sharedFlow.collect() {
            println("2nd $it")
        }
    }

    delay(5000)

}

private suspend fun getData() {
    mutableSharedFlow.emit(PersonOne())
    delay(1000)
    mutableSharedFlow.emit(PersonOne())
}

private class PersonOne