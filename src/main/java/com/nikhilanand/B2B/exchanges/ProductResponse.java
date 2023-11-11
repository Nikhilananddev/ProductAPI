package com.nikhilanand.B2B.exchanges;

import com.nikhilanand.B2B.dto.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    @NotNull
    private Product product;
}
