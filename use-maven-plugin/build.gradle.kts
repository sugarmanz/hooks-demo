tasks {
    val clean by creating {
        doLast {
            exec {
                commandLine("/usr/local/bin/mvn", "clean", "-e")
            }
        }
    }

    val build by creating {
        dependsOn(clean)
        doLast {
            exec {
                commandLine("/usr/local/bin/mvn", "install", "-e")
            }
        }
    }

    create("run") {
        dependsOn(build)
        group = "application"
        doLast {
            exec {
                commandLine("java", "-jar", "${project.projectDir}/target/hooks-plugin-maven-example-0.0.0-SNAPSHOT-jar-with-dependencies.jar")
            }
        }
    }
}