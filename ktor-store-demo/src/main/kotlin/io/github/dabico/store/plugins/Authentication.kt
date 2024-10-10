package io.github.dabico.store.plugins

import io.ktor.server.application.*
import io.ktor.server.auth.*

fun Application.configureAuthentication() {
    install(Authentication) {
        basic {
            validate {
                if (it.name == "admin" && it.password == "password")
                    UserIdPrincipal(it.name)
                else null
            }
        }
    }
}
