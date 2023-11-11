package com.nikhilanand.B2B.exchanges;

import com.nikhilanand.B2B.global.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddProductRequest {


    @NotNull
    private String name;

    @NotNull
    private String Description;

    @NotNull
    private int price;

    @NotNull
    private Status availability;

}
