package com.linkedinlearning.springbootstoredemo.converter

import com.linkedinlearning.springbootstoredemo.model.Product
import com.linkedinlearning.springbootstoredemo.ui.ProductViewItem
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
