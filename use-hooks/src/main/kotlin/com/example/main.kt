package com.example

import com.example.car.*
import kotlinx.coroutines.runBlocking


fun main() {
    val car = Car(CarLoggerPlugin)

    car.speed = 88
    car.speed = 70
}