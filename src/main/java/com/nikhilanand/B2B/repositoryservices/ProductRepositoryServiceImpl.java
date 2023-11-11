package com.nikhilanand.B2B.repositoryservices;

import com.nikhilanand.B2B.dto.Product;
import com.nikhilanand.B2B.global.Status;
import com.nikhilanand.B2B.model.ProductEntity;
import com.nikhilanand.B2B.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.inject.Provider;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductRepositoryServiceImpl implements ProductRepositoryService {

    @Autowired
    ProductRepository productRepository;


    @Autowired
    private Provider<ModelMapper> modelMapperProvider;

    @Override
    public Product addProduct(String name, String description, int price, Status availability) {

        ModelMapper modelMapper = modelMapperProvider.get();
        Product product = null;
        try {
            ProductEntity productEntity = new ProductEntity();
            productEntity.setName(name);
            productEntity.setDescription(description);
            productEntity.setPrice(price);
            productEntity.setAvailability(availability);
            productEntity = productRepository.save(productEntity);
            product = modelMapper.map(productEntity, Product.class);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return product;

    }

    @Override
    public Product getProduct(String productId) {

        ModelMapper modelMapper = modelMapperProvider.get();
        Product product = null;

        Optional<ProductEntity> productEntity = productRepository.findByProductId(productId);

        if (productEntity.isEmpty())
            return null;
        else {
            product = modelMapper.map(productEntity, Product.class);

        }
        return product;
    }

    @Override
    public List<Product> getProducts() {
        ModelMapper modelMapper = modelMapperProvider.get();

        List<Product> products = new ArrayList<>();
        List<ProductEntity> productEntities = productRepository.findAll();

        for (ProductEntity entity : productEntities) {
            Product product = new Product();
            product = modelMapper.map(entity, Product.class);
            products.add(product);
        }

        return products;
    }

    @Override
    public Product updateProduct(String productId, String name, String description, int price, Status availability) {

        ModelMapper modelMapper = modelMapperProvider.get();

        Optional<ProductEntity> productEntity = productRepository.findByProductId(productId);

        Product product = null;

        if (productEntity.isPresent()) {
            ProductEntity entity = productEntity.get();
            entity.setName(name);
            entity.setDescription(description);
            entity.setPrice(price);
            entity.setAvailability(availability);
            productRepository.save(entity);
            product = modelMapper.map(productEntity, Product.class);
            return product;
        }

        return product;

    }

    @Override
    public Product deleteProduct(String productId) {
        ModelMapper modelMapper = modelMapperProvider.get();

        Product product = null;

        Optional<ProductEntity> productEntity = productRepository.findByProductId(productId);
        if (productEntity.isPresent()) {
            product = modelMapper.map(productEntity, Product.class);
            productRepository.deleteById(productId);

        }

        return product;
    }
}
