package com.bychkova.elena.Vending.service;

import com.bychkova.elena.Vending.entity.Product;
import com.bychkova.elena.Vending.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public Product createProduct(String name, double price) {
        return productRepository.save(new Product(name, price));
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    public Product changeProductPrice(Long id, double price) {
        return productRepository.findById(id).map(product -> {
                    product.setPrice(price);
                    return productRepository.save(product);
                }).orElseThrow(() -> new RuntimeException("Product not found"));
    }
}
