package io.github.dabico.store.ui

/**
 * A UI-facing representation of a [io.github.dabico.store.model.Product].
 */
data class ProductViewItem(
    var name: String,
    var description: String,
    var thumbnail: String,
    var displayDetails: String
)
