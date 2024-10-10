package io.github.dabico.store.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get {
            call.response.status(HttpStatusCode.OK)
        }

        get("/products") {
            call.respondText { "List of products!" }
        }
    }
}
