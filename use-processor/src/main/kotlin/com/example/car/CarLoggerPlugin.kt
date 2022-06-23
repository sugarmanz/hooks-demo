package com.example.car

object CarLoggerPlugin : Car.Plugin {
    override fun apply(car: Car) {
        car._hooks.brake.tap("logging-brake-hook") {
            println("Turning on brake lights")
        }

        car._hooks.accelerate.tap("logging-accelerate-hook") { newSpeed ->
            println("Accelerating to $newSpeed")
        }
    }
}