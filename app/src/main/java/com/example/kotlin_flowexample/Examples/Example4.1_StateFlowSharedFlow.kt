package com.example.kotlin_flowexample

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

// Когда мы создаем поток через flow{}-билдер, то он является холодным.

// Отличия,

// Первое
// - холодный Flow эмитит значение только если на них кто то подписался.
// - горячий Flow эмитит значение независимо от того есть подписчики или нет
// Второе
// - при каждой подписки создается на холодный Flow создается новый поток данных,
// - при подписке на горячий Flow этого не происходит и все подписчики будут получать одни и те же элементы.
// Третье
// - если у холодный Flow подписчикам больше не нужны данные, то они завершают свою работу.
// - горячие Flowг продолжат эмитить данные независимо от того нужны эти данные подписчикам или нет
// Четвертое
// - если в холодном Flow больше нет данных, то он завершают свою работу.
// - горячие Flowг не завершается никогда

private val mutableSharedFlow = MutableSharedFlow<String>()
private val sharedFlow : SharedFlow<String> = mutableSharedFlow

suspend fun main() {
    // Кейс 1 ничего не происходт

    getData()

    GlobalScope.launch {
        delay(1000)
        sharedFlow.collect() {
            println(it)
        }
    }

    delay(5000)

}

private suspend fun getData() {
    delay(1000)
    mutableSharedFlow.emit("b")
}