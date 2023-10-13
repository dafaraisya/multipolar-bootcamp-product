package com.multipolar.bootcamp.product.service;

import com.multipolar.bootcamp.product.domain.Product;
import com.multipolar.bootcamp.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(String id) {
        return productRepository.findById(id);
    }

    public Product createOrUpdateProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProductById(String id) {
        productRepository.deleteById(id);
    }

    public List<Product> getProductByName(String productName) {
        return productRepository.findByProduct(productName);
    }
}
