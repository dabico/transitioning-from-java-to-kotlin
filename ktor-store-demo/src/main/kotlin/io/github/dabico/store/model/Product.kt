package io.github.dabico.store.model

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val upc: Int,
    val name: String,
    val description: String
)
