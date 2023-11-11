package com.nikhilanand.B2B.exchanges;

import com.nikhilanand.B2B.dto.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GetProductsResponse {
    List<Product> productList = new ArrayList<>();
}
