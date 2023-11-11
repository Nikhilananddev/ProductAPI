package com.nikhilanand.B2B.services;

import com.nikhilanand.B2B.dto.Product;
import com.nikhilanand.B2B.exchanges.AddProductRequest;
import com.nikhilanand.B2B.exchanges.GetProductsResponse;
import com.nikhilanand.B2B.exchanges.ProductResponse;
import com.nikhilanand.B2B.exchanges.UpdateProductRequest;
import com.nikhilanand.B2B.repositoryservices.ProductRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepositoryService productRepositoryService;

    @Override
    public GetProductsResponse getProducts() {

        List<Product> products = productRepositoryService.getProducts();
        GetProductsResponse getProductsResponse = new GetProductsResponse(products);

        if (products != null) {
            return getProductsResponse;
        }

        return null;
    }

    @Override
    public ProductResponse getProductById(String productId) {
        Product product = productRepositoryService.getProduct(productId);
        ProductResponse productResponse = new ProductResponse(product);
        return productResponse;
    }

    @Override
    public ProductResponse addProduct(AddProductRequest addProductRequest) {

        System.out.println("Hello service" + addProductRequest.toString());


        System.out.println("service" + addProductRequest);

        Product product = productRepositoryService.addProduct(addProductRequest.getName(),
                addProductRequest.getDescription(),
                addProductRequest.getPrice(), addProductRequest.getAvailability()
        );

        ProductResponse productResponse = new ProductResponse(product);

        return productResponse;
    }

    @Override
    public ProductResponse updateProduct(UpdateProductRequest updateProductRequest) {

        System.out.println("service" + updateProductRequest.toString());
        Product product = null;
        product = productRepositoryService.updateProduct(updateProductRequest.getProductId(), updateProductRequest.getName(),
                updateProductRequest.getDescription(), updateProductRequest.getPrice(), updateProductRequest.getAvailability());

        if (product != null) {
            ProductResponse productResponse = new ProductResponse(product);
            return productResponse;
        }
        return null;
    }

    @Override
    public ProductResponse removeProduct(String productId) {
        Product product = null;
        product = productRepositoryService.deleteProduct(productId);
        if (product != null)
            return new ProductResponse(product);

        return null;
    }
}
