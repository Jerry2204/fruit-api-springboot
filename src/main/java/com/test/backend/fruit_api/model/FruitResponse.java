package com.test.backend.fruit_api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FruitResponse {

    private String id;

    private String name;

    private String color;

    private String origin;

    private Integer price;

}
