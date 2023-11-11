package com.nikhilanand.B2B.repositoryservices;

import com.nikhilanand.B2B.dto.Product;
import com.nikhilanand.B2B.global.Status;

import java.util.List;

public interface ProductRepositoryService {
    Product addProduct(String name, String Description, int price, Status availability);

    Product getProduct(String productId);

    List<Product> getProducts();

    Product updateProduct(String productId, String name, String Description, int price, Status availability);

    Product deleteProduct(String productId);

}
