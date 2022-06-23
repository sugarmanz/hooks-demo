pluginManagement {
    val KOTLIN_VERSION: String by settings
    val HOOKS_VERSION: String by settings

    repositories {
        mavenLocal()
        gradlePluginPortal()
    }

    plugins {
        kotlin("jvm") version KOTLIN_VERSION
        id("com.intuit.hooks") version HOOKS_VERSION
    }
}

include(":use-hooks", ":use-processor", ":use-gradle-plugin", ":use-maven-plugin")