package com.nikhilanand.B2B.repositories;

import com.nikhilanand.B2B.model.ProductEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProductRepository extends MongoRepository<ProductEntity, String> {
    Optional<ProductEntity> findByProductId(String productId);


}
