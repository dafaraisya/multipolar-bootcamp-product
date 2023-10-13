package com.multipolar.bootcamp.product.controller;

import com.multipolar.bootcamp.product.domain.Product;
import com.multipolar.bootcamp.product.dto.ErrorMessage;
import com.multipolar.bootcamp.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@Valid @RequestBody Product product,
                                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<ErrorMessage> validationErrors = new ArrayList<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                ErrorMessage errorMessage = new ErrorMessage();
                errorMessage.setCode("VALIDATION_ERROR");
                errorMessage.setMessage(error.getDefaultMessage());
                validationErrors.add(errorMessage);
            }
            return ResponseEntity.badRequest().body(validationErrors);
        }
        Product createdProduct = productService.createOrUpdateProduct(product);
        return ResponseEntity.ok(createdProduct);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Optional<Product> getProductById(@PathVariable String id) {
        return productService.getProductById(id);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable String id, @RequestBody Product product) {
        product.setId(id);
        return productService.createOrUpdateProduct(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable String id) {
        productService.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("product/{productName}")
    public ResponseEntity<List<Product>> getProductByName(@PathVariable String productName) {
        List<Product> products = productService.getProductByName(productName);
        return ResponseEntity.ok(products);
    }
}
