package io.github.dabico.store.plugins

import io.github.dabico.store.model.Product
import io.github.dabico.store.persistance.ProductDatabase
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get {
            call.response.status(HttpStatusCode.OK)
        }

        get("/products") {
            call.respond(ProductDatabase.dao.products())
        }

        get("/products/{upc}") {
            val upc = call.parameters["upc"]?.toIntOrNull()
            if (upc == null) call.respond(HttpStatusCode.BadRequest)
            else when (val product = ProductDatabase.dao.product(upc)) {
                null -> call.respond(HttpStatusCode.NotFound)
                else -> call.respond(product)
            }
        }

        authenticate {
            post("/add-product") {
                val parsed = call.receive<Product>()
                if (ProductDatabase.dao.exists(parsed.upc))
                    call.respond(HttpStatusCode.Conflict)
                else
                    ProductDatabase.dao.addProduct(parsed)
                        ?.let { call.respond(HttpStatusCode.Created, it) }
                        ?:run { call.respond(HttpStatusCode.BadRequest) }
            }
        }
    }
}
