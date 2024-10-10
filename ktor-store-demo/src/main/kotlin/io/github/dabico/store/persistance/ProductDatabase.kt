package io.github.dabico.store.persistance

import io.github.dabico.store.model.Product
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object ProductDatabase {

    init {
        val driverClassName = "org.h2.Driver"
        val jdbcUrl = "jdbc:h2:file:./build/db"
        val database = Database.connect(jdbcUrl, driverClassName)

        transaction {
            SchemaUtils.create(ProductTable)
        }
    }

    val dao = ProductDAO().apply {
        runBlocking {
            if (products().isEmpty())
                addProduct(
                    Product(
                        upc = 1,
                        name = "Coffee Mug",
                        description = "Simple coffee mug",
                    )
                )
        }
    }
}