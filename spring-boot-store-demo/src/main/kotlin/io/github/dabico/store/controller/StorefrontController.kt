package io.github.dabico.store.controller

import io.github.dabico.store.service.ProductService
import io.github.dabico.store.ui.ProductViewItem
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.convert.ConversionService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.ModelAndView

/**
 * Controls the public-facing storefront for browsing available products
 */
@Controller
class StorefrontController @Autowired constructor(
    private val productService: ProductService,
    private val conversionService: ConversionService
) {

    @GetMapping("/storefront")
    fun showProducts(): ModelAndView {
        val products = productService.allProducts()
        val viewItems = products.map { conversionService.convert(it, ProductViewItem::class.java) }
        return ModelAndView("Storefront", hashMapOf("products" to viewItems.toList()))
    }
}
