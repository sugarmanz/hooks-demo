package com.example.car

import com.intuit.hooks.HookContext
import com.intuit.hooks.SyncHook

class Car(private vararg val plugins: Plugin) {

    private val _hooks = HooksImpl()

    val hooks: Hooks by ::_hooks

    var speed: Int = 0
        set(value) {
            if (value < field) _hooks.brake.call()

            field = value
            _hooks.accelerate.call(value)
        }

    init {
        plugins.forEach { it.apply(this) }
    }

    interface Plugin {
        fun apply(car: Car)
    }

    abstract class Hooks {
        abstract val brake: SyncHook<(HookContext) -> Unit>
        abstract val accelerate: SyncHook<(HookContext, Int) -> Unit>
    }

    private class HooksImpl : Hooks() {

        override val brake = BrakeHook()

        class BrakeHook : SyncHook<(HookContext) -> Unit>() {
            fun call() = super.call { f, context -> f(context) }
        }

        override val accelerate = AccelerateHook()

        class AccelerateHook : SyncHook<(HookContext, Int) -> Unit>() {
            fun call(newSpeed: Int) = super.call { f, context -> f(context, newSpeed) }
            fun tap(name: String, f: ((newSpeed: Int) -> Unit)) = super.tap(name) { _: HookContext, newSpeed: Int -> f(newSpeed) }
        }

    }
}

