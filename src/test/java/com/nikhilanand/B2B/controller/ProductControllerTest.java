package com.nikhilanand.B2B.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nikhilanand.B2B.B2BApplication;
import com.nikhilanand.B2B.dto.Product;
import com.nikhilanand.B2B.exchanges.GetProductsResponse;
import com.nikhilanand.B2B.exchanges.ProductResponse;
import com.nikhilanand.B2B.global.Status;
import com.nikhilanand.B2B.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest(classes = {B2BApplication.class})
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
@DirtiesContext
@ActiveProfiles("test"
)
class ProductControllerTest {
    private static final String PRODUCT_API_URI = "http://localhost:8081/";
    @MockBean
    ProductService productService;
    @InjectMocks
    ProductController productController;
    private ObjectMapper objectMapper;
    private MockMvc mvc;


    @BeforeEach
    public void setup() {
        objectMapper = new ObjectMapper();

        MockitoAnnotations.openMocks(this);

        mvc = MockMvcBuilders.standaloneSetup(productController).build();

    }


    @Test
    void correctUrlReturnsOkResponseAndListOfProduct() throws Exception {

        URI uri = UriComponentsBuilder
                .fromUriString(PRODUCT_API_URI)
                .pathSegment("products")
                .build()
                .toUri();


        assertEquals(PRODUCT_API_URI + "products", uri.toString());


        Product product = new Product("654f5799a9ca3d0976c00d37", "iphone 14", "6 inch",
                1900000, Status.AVAILABLE);
        Product product2 = new Product("654f5799a9ca3d0976c00d37", "iphone 14", "6 inch",
                1900000, Status.AVAILABLE);
        List<Product> productList = new ArrayList<>();

        productList.add(product);
        productList.add(product2);


        GetProductsResponse getProductsResponse = new GetProductsResponse(productList);

        when(productService.getProducts()).thenReturn(getProductsResponse);


        MockHttpServletResponse response = mvc.perform(
                get(uri.toString()).accept(APPLICATION_JSON_UTF8)
        ).andReturn().getResponse();


        assertEquals(HttpStatus.OK.value(), response.getStatus());


        GetProductsResponse responseListResponse = objectMapper.readValue(response.getContentAsString(), new TypeReference<GetProductsResponse>() {
        });


        // Verify the size of the product list
        assertEquals(2, responseListResponse.getProductList().size());


    }

    @Test
    void getProductById() throws Exception {

        URI uri = UriComponentsBuilder
                .fromUriString(PRODUCT_API_URI)
                .pathSegment("products", "654f5799a9ca3d0976c00d37")
                .build()
                .toUri();


        Product product = new Product("654f5799a9ca3d0976c00d37", "iphone 14", "6 inch",
                1900000, Status.AVAILABLE);

        ProductResponse productResponse = new ProductResponse(product);

        when(productService.getProductById(anyString())).thenReturn(productResponse);


        MockHttpServletResponse response = mvc.perform(
                get(uri.toString()).accept(APPLICATION_JSON_UTF8)
        ).andReturn().getResponse();


        assertEquals(HttpStatus.OK.value(), response.getStatus());


        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor
                .forClass(String.class);


        verify(productService, times(1))
                .getProductById(argumentCaptor.capture());

        assertEquals("654f5799a9ca3d0976c00d37", argumentCaptor.getValue());


    }
}