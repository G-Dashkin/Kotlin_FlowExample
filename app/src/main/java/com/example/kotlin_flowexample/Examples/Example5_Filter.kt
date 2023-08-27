package com.example.kotlin_flowexample

import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        flow {
            for (i in 1..10) {
                emit(i)
                println(i)
            }
        }.filter { i:Int -> i<5 }.collect{
            println("Filter: $it" )
        }
    }
}