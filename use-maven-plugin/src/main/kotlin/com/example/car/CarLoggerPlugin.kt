package com.example.car

object CarLoggerPlugin : Car.Plugin {
    override fun apply(car: Car) {
        car.hooks.brake.tap("logging-brake-hook") {
            println("Turning on brake lights")
        }

        car.hooks.accelerate.tap("logging-accelerate-hook") { newSpeed ->
            println("Accelerating to $newSpeed")
        }
    }
}