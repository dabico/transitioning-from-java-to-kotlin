package io.github.dabico.store.persistance

import io.github.dabico.store.model.Product
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

class ProductDAO {

    suspend fun products(): List<Product> {
        return query {
            ProductTable.selectAll().map { it.toProduct() }
        }
    }

    suspend fun product(upc: Int): Product? {
        return query {
            ProductTable.selectAll().where { ProductTable.upc eq upc }.firstOrNull()?.toProduct()
        }
    }

    suspend fun exists(upc: Int): Boolean {
        return query {
            !ProductTable.selectAll().where { ProductTable.upc eq upc }.empty()
        }
    }

    suspend fun addProduct(product: Product): Product? {
        return query {
            try {
                ProductTable.insert {
                    it[upc] = product.upc
                    it[name] = product.name
                    it[description] = product.description
                }.resultedValues?.singleOrNull()?.toProduct()
            } catch (e: Exception) {
                null
            }
        }
    }

    private fun ResultRow.toProduct(): Product {
        return Product(
            upc = this[ProductTable.upc],
            name = this[ProductTable.name],
            description = this[ProductTable.description]
        )
    }

    private suspend fun <T> query(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }
}