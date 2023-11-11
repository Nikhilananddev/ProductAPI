package com.nikhilanand.B2B.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nikhilanand.B2B.B2BApplication;
import com.nikhilanand.B2B.dto.Product;
import com.nikhilanand.B2B.exchanges.ProductResponse;
import com.nikhilanand.B2B.exchanges.UpdateProductRequest;
import com.nikhilanand.B2B.global.Status;
import com.nikhilanand.B2B.repositoryservices.ProductRepositoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {B2BApplication.class})
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
@DirtiesContext
@ActiveProfiles("test"
)
class ProductServiceImplTest {


    @MockBean
    ProductRepositoryService productRepositoryService;

    @InjectMocks
    ProductServiceImpl productService;

    private ObjectMapper objectMapper;


    @BeforeEach
    public void setup() {
        objectMapper = new ObjectMapper();

        MockitoAnnotations.openMocks(this);


    }


    @Test
    void getProductById() {
        Product product = new Product("654f5799a9ca3d0976c00d37", "iphone 14", "6.7 inch",
                1900000, Status.AVAILABLE);
        when(productRepositoryService.getProduct(anyString())).thenReturn(product);


        ProductResponse productResponse = productService.getProductById("654f5799a9ca3d0976c00d37");

        assertEquals("654f5799a9ca3d0976c00d37", productResponse.getProduct().getProductId());
        assertEquals("iphone 14", productResponse.getProduct().getName());


    }

    @Test
    void updateProduct() {

        Product product = new Product("654f5799a9ca3d0976c00d37", "iphone 14", "6.7 inch",
                1900000, Status.AVAILABLE);
        when(productRepositoryService.updateProduct("654f5799a9ca3d0976c00d37", "iphone 14", "6.7 inch",
                1900000, Status.AVAILABLE)).thenReturn(product);


        UpdateProductRequest updateProductRequest = new UpdateProductRequest("654f5799a9ca3d0976c00d37", "iphone 14", "6.7 inch",
                1900000, Status.AVAILABLE);

        ProductResponse productResponse = productService.updateProduct(updateProductRequest);
        assertEquals("iphone 14", productResponse.getProduct().getName());

        assertEquals("6.7 inch", productResponse.getProduct().getDescription());


    }
}