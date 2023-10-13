package com.multipolar.bootcamp.product.repository;

import com.multipolar.bootcamp.product.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {
    @Query("{'productName' : { $regex: ?0, $options: 'i' }}")
    List<Product> findByProduct(String productName);
}
