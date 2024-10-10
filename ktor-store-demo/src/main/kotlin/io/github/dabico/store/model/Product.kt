package io.github.dabico.store.model

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val upc: String,
    val name: String,
    val description: String,
)

val IN_MEMORY_PRODUCTS = listOf(
    Product("9713", "Travel Backpack", "A nice backpack for your trip"),
    Product("1235", "RFID Passport Protector", "To keep your passport safe"),
    Product("1167", "Fancy Carry-On Bag", "A nice carry-on bag for your daily outings"),
)
