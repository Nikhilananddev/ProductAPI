package com.nikhilanand.B2B.controller;

import com.nikhilanand.B2B.exchanges.AddProductRequest;
import com.nikhilanand.B2B.exchanges.GetProductsResponse;
import com.nikhilanand.B2B.exchanges.ProductResponse;
import com.nikhilanand.B2B.exchanges.UpdateProductRequest;
import com.nikhilanand.B2B.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/products")
    ResponseEntity<GetProductsResponse> getProducts() {
        GetProductsResponse getProductsResponse = productService.getProducts();

        if (getProductsResponse != null) {
            return ResponseEntity.ok().body(getProductsResponse);
        }

        return ResponseEntity.badRequest().body(null);
    }


    @GetMapping("/products/{product_id}")
    ResponseEntity<?> getProducts(@PathVariable(value = "product_id") String productId) {

        ProductResponse productResponse = productService.getProductById(productId);

        if (productResponse != null)
            return ResponseEntity.ok(productResponse);


        return ResponseEntity.badRequest().body(null);


    }

    @PostMapping("/products")
    ResponseEntity<ProductResponse> addProducts(@RequestBody AddProductRequest addProductRequest) {

        ProductResponse productResponse = productService.addProduct(addProductRequest);

        if (Objects.equals(productResponse.getProduct().getName(), addProductRequest.getName()))
            return ResponseEntity.ok().body(productResponse);
        else
            return ResponseEntity.badRequest().body(null);

    }

    @PutMapping("/products/{product_id}")
    ResponseEntity<?> updateProduct(@PathVariable(value = "product_id") String productId, @RequestBody UpdateProductRequest updateProductRequest) {
        ProductResponse productResponse = productService.updateProduct(updateProductRequest);

        if (productResponse != null)
            return ResponseEntity.ok().body(productResponse);
        else
            return ResponseEntity.badRequest().body(null);
    }


    @DeleteMapping("/products/{product_id}")
    ResponseEntity<?> updateProduct(@PathVariable(value = "product_id") String productId) {
        ProductResponse productResponse = productService.removeProduct(productId);

        if (productResponse != null)
            return ResponseEntity.ok(productResponse);


        return ResponseEntity.badRequest().body(null);
    }


}
