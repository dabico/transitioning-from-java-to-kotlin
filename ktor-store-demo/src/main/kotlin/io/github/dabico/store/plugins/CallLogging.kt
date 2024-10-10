package io.github.dabico.store.plugins

import io.ktor.server.application.*
import io.ktor.server.plugins.callloging.*

fun Application.configureCallLogging() {
    install(CallLogging)
}