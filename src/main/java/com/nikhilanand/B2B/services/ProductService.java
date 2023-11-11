package com.nikhilanand.B2B.services;

import com.nikhilanand.B2B.exchanges.AddProductRequest;
import com.nikhilanand.B2B.exchanges.GetProductsResponse;
import com.nikhilanand.B2B.exchanges.ProductResponse;
import com.nikhilanand.B2B.exchanges.UpdateProductRequest;

public interface ProductService {


    GetProductsResponse getProducts();

    ProductResponse getProductById(String id);

    ProductResponse addProduct(AddProductRequest addProductRequest);

    ProductResponse updateProduct(UpdateProductRequest updateProductRequest);

    ProductResponse removeProduct(String id);

}
