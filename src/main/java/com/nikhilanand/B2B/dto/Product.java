package com.nikhilanand.B2B.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nikhilanand.B2B.global.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {

    @JsonIgnore
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
