package com.example.car

import com.intuit.hooks.SyncHook
import com.intuit.hooks.dsl.HooksDsl

class Car(private vararg val plugins: Plugin) {

    abstract class Hooks : HooksDsl() {
        @Sync<() -> Unit>
        abstract val brake: SyncHook<*>

        @Sync<(newSpeed: Int) -> Unit>
        abstract val accelerate: SyncHook<*>
    }

    val hooks = CarHooksImpl()

    var speed: Int = 0
        set(value) {
            if (value < field) hooks.brake.call()

            field = value
            hooks.accelerate.call(value)
        }

    init {
        plugins.forEach { it.apply(this) }
    }

    interface Plugin {
        fun apply(car: Car)
    }
}

