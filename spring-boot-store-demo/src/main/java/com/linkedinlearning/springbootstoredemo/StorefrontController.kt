package com.linkedinlearning.springbootstoredemo

import com.linkedinlearning.springbootstoredemo.service.ProductService
import com.linkedinlearning.springbootstoredemo.ui.ProductViewItem
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
        val productViewItems = products.stream().map {
                product -> conversionService.convert(product, ProductViewItem::class.java)
        }
        return ModelAndView("Storefront", hashMapOf("products" to productViewItems.toList()))
    }
}
