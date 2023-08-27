package com.example.kotlin_flowexample

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.runBlocking

fun main() {

    val flow1 = flow<Int> {
        for (i in 1..3) {
            emit(i)
        }
    }

    val flow2 = flow<String> {
        val list = listOf("A", "B", "C", "D")
        for (i in list) {
            emit(i)
            delay(2000)
        }
    }


    runBlocking {
        flow1.zip(flow2) {
            a: Int, b: String -> "$a:$b"
        }.collect{
            println(it)
        }
    }

}