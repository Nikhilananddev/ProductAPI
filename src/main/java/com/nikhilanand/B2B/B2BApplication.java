package com.nikhilanand.B2B;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
@OpenAPIDefinition(info = @Info(title = "Product API", version = "2.0", description = "Products"))
@SpringBootApplication
public class B2BApplication {

    public static void main(String[] args) {
        SpringApplication.run(B2BApplication.class, args);


    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
