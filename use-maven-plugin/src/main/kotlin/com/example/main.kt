package com.example

import com.example.car.Car
import com.example.car.CarLoggerPlugin


fun main() {
    val car = Car(CarLoggerPlugin)

    car.speed = 88
    car.speed = 70
}
