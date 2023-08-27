package com.example.kotlin_flowexample

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking

fun main() {

    runBlocking {
        println ("получение чисел Фибоначчи" )
        sendFibonacciNumbersV2().collect {
            println ("сбор чисел Фибоначчи: $it ")
        }
        println ("Завершить сбор чисел Фибоначчи")
    }

}

// Корутины могут преобразовать коллекцию Kotlin в коллекцию flow, которая выдает значения
// того же типа. Итак, наша функция, отправляющая числа Фибоначчи, может быть записана так:
fun sendFibonacciNumbersV2(): Flow<Int> {
    return listOf (0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55).asFlow()
}

// Другой способ создать Flow – использовать функцию flowOf(vararg elements: T), которая будет
// испускать все элементы, переданные в качестве параметра. В нашем коде функция будет так:
fun sendFibonacciNumbersV3() = flowOf (0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55)
