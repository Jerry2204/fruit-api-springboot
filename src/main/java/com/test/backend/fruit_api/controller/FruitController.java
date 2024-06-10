package com.test.backend.fruit_api.controller;

import com.test.backend.fruit_api.entity.Fruit;
import com.test.backend.fruit_api.service.FruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FruitController {

    @Autowired
    private FruitService fruitService;

    @GetMapping(
            path = "/api/fruits",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<Fruit> getAllFruits() {
        return fruitService.getAllFruits();
    }

}
