package com.example.kotlin_flowexample

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

// Рассмотрим случай, как мы можем создавать и потреблять данные, используя потоки в корутине.
// Реализуем функцию, которая возвращает Flow<Integer> целое число, эта функция даст нам числа Фибоначчи.
// Добавим задержку между каждым числом, делая вид, что есть некоторая работа, которую мы должны выполнить.
// В основной функции мы собираем числа, выдаваемые этой функцией, и печатем сообщение, содержащее
// собранное значение.
fun main() {

    runBlocking {
        println ("получение чисел Фибоначчи" )
        sendFibonacciNumbers().collect {
            println ("сбор чисел Фибоначчи: $it ")
        }
        println ("Завершить сбор чисел Фибоначчи")
    }

}

// Здесь поток выдает значение, вызывая функцию emit(value). Как только мы подпишемся на поток
// использующий функции collect(), мы сможем использовать данные, выдаваемые нашим потоком.
// В этом примере мы попробовали один из способов для создания построителя потока. Но есть и другие
// способы создать поток.
suspend fun sendFibonacciNumbers(): Flow<Int> {
    return flow {
        val fibonacciNumbersList = listOf(0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55)
        fibonacciNumbersList.forEach {
            delay( it * 100L)
            emit( it )
        }
    }
}