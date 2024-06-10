package com.test.backend.fruit_api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "fruits")
public class Fruit {

    @Id
    private String id;

    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "color is required")
    private String color;

    @NotBlank(message = "origin is required")
    private String origin;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be greater than zero")
    private Integer price;

    private boolean deleted = false;
}
