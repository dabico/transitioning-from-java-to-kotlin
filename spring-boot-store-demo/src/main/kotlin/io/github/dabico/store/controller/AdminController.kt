package io.github.dabico.store.controller

import io.github.dabico.store.model.Product
import io.github.dabico.store.service.ProductService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.servlet.ModelAndView

/**
 * Controls the private admin portal for the site.
 * The admin area supports a minimal inventory view, and a form for adding new products
 */
@Controller
class AdminController @Autowired constructor(private val productService: ProductService) {

    private val logger = LoggerFactory.getLogger(AdminController::class.java)

    @GetMapping("/admin")
    fun showProducts(): ModelAndView {
        val products = productService.allProducts()
        return ModelAndView("Admin", hashMapOf("products" to products))
    }

    @GetMapping("/admin/new-product")
    fun newProduct(): ModelAndView {
        return ModelAndView("ProductEntryForm")
    }

    @PostMapping("/admin/new-product")
    fun formPost(product: Product, model: Model): String {
        val created = productService.insert(product)
        logger.info("/admin/new-product -> ${created.name}")
        model.addAttribute("product", created)
        return "ProductEntryForm"
    }
}