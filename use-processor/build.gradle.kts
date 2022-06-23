plugins {
    id("com.google.devtools.ksp") version "1.6.21-1.0.5"
}


dependencies {
    val HOOKS_VERSION: String by project

    ksp("com.intuit.hooks", "compiler-plugin", HOOKS_VERSION)
    api("com.intuit.hooks", "hooks", HOOKS_VERSION)
}

kotlin {
    sourceSets.main {
        kotlin.srcDir("build/generated/ksp/main/kotlin")
    }
}
