package com.test.backend.fruit_api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

    private String name;

    private String color;

    private String origin;

    private Integer price;

}
