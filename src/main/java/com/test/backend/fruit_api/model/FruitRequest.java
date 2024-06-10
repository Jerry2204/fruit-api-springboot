package com.test.backend.fruit_api.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FruitRequest {

    @NotBlank(message = "name is required")
    @Size(max = 100)
    private String name;

    @NotBlank(message = "color is required")
    @Size(max = 100)
    private String color;

    @NotBlank(message = "origin is required")
    @Size(max = 100)
    private String origin;

    @NotBlank(message = "price is required")
    private Integer price;


}
