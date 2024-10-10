plugins {
    kotlin("jvm") version "1.7.10"
    id("io.ktor.plugin") version "2.3.12"
}

group = "io.github.dabico.store"
version = "0.0.1"

application {
    mainClass.set("io.github.dabico.store.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.ktor:ktor-server-netty-jvm")
    implementation("io.ktor:ktor-server-call-logging-jvm")
    implementation("ch.qos.logback:logback-classic:1.5.9")
}
