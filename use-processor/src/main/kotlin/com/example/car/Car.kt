package com.example.car

import com.intuit.hooks.SyncHook
import com.intuit.hooks.dsl.HooksDsl


class Car(private vararg val plugins: Plugin) {

    interface Hooks {
        val brake: SyncHook<*>
        val accelerate: SyncHook<*>
    }

    abstract class MutableHooks : HooksDsl(), Hooks {
        @Sync<() -> Unit> abstract override val brake: SyncHook<*>
        @Sync<(newSpeed: Int) -> Unit> abstract override val accelerate: SyncHook<*>
    }

    val _hooks = CarMutableHooksImpl()

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
}

