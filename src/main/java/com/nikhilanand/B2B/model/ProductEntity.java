package com.nikhilanand.B2B.model;

import com.nikhilanand.B2B.global.Status;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Data
@Document("Product")
@NoArgsConstructor
public class ProductEntity {

    @Id
    private String productId;

    @NotNull
    private String name;

    @NotNull
    private String Description;

    @NotNull
    private int price;

    @NotNull
    private Status availability;


}
