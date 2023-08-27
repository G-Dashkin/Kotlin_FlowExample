package com.example.kotlin_flowexample

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

suspend fun main(){

    // В качестве объектов в поток добавляются квадраты чисел от 1 до 5
    getNumbers().collect { number -> println(number) }


    // Важно!!! Асинхронный поток не запускается, пока не будет применена терминальная операция над
    // получаемыми даными, например, функция collect()
    val numberFlow = getNumbers()       // поток создан, но не запущен
    println("numberFlow has created")
    println("launch collect function")
    numberFlow.collect { number -> println(number) }   // запуск потока
}

// Пример создадия и использования асинхронного потока чисел:
fun getNumbers(): Flow<Int> = flow{
    println("numberFlow has started")
    for(item in 1..5){
        emit(item * item)
    }
}
