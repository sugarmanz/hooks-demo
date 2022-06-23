allprojects {
    repositories {
        jcenter()
        mavenLocal()
        maven("https://oss.jfrog.org/artifactory/oss-snapshot-local/")
    }
}

plugins {
    kotlin("jvm")
}

subprojects {
    if (name.contains("maven")) return@subprojects
    apply(plugin = "kotlin")
    apply(plugin = "application")

    dependencies {
        val COROUTINES_VERSION: String by project
        implementation(kotlin("stdlib"))
        implementation("org.jetbrains.kotlinx", "kotlinx-coroutines-core", COROUTINES_VERSION)
    }

    configure<JavaApplication> {
        mainClass.set("com.example.MainKt")
    }
}