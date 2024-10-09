package io.github.dabico.store.service

import io.github.dabico.store.model.Product
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.BeanPropertyRowMapper
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service

/**
 * Encapsulates supported database interactions to be used by application controllers
 */
@Service
class ProductService @Autowired constructor(private val jdbcTemplate: JdbcTemplate) {

    fun allProducts(): List<Product> {
        return jdbcTemplate.query(
            "SELECT * FROM products",
            BeanPropertyRowMapper.newInstance(Product::class.java)
        )
    }

    fun insert(product: Product): Product {
        jdbcTemplate.update(
            "INSERT INTO products VALUES(?, ?, ?, ?, ?)",
            product.upc,
            product.name,
            product.type,
            product.description,
            product.thumbnail
        )
        return product
    }
}
