package com.bychkova.elena.Vending.controller;

import com.bychkova.elena.Vending.dto.PriceChangeRequest;
import com.bychkova.elena.Vending.entity.Product;
import com.bychkova.elena.Vending.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public Iterable<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product.getName(), product.getPrice());
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
    }

    @PutMapping("/{id}")
    public Product changeProductPrice(@PathVariable Long id, @RequestBody PriceChangeRequest request) {
        return productService.changeProductPrice(id, request.getPrice());
    }
}
