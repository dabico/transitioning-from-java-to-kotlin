package io.github.dabico.store.converter

import io.github.dabico.store.model.Product
import io.github.dabico.store.ui.ProductViewItem
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class ProductToProductViewItemConverter : Converter<Product, ProductViewItem> {

    override fun convert(source: Product): ProductViewItem {
        return ProductViewItem(
            source.name,
            source.description,
            source.thumbnail,
            "${source.type}:${source.upc}"
        )
    }
}
