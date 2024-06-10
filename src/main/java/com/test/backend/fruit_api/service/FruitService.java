package com.test.backend.fruit_api.service;

import com.test.backend.fruit_api.entity.Fruit;
import com.test.backend.fruit_api.entity.User;
import com.test.backend.fruit_api.model.FruitResponse;
import com.test.backend.fruit_api.repository.FruitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FruitService {

    @Autowired
    private FruitRepository fruitRepository;

    public List<FruitResponse> findAllFruits() {
        List<Fruit> fruits = fruitRepository.findAllActiveFruits();

        return fruits.stream()
                .map(this::toFruitResponse)
                .collect(Collectors.toList());
    }

    public List<Fruit> getAllFruits() {
        return fruitRepository.findAllActiveFruits();
    }

    public Fruit createFruit(Fruit fruit) {
        fruit.setId(UUID.randomUUID().toString());
        return fruitRepository.save(fruit);
    }

    public Optional<Fruit> getFruitById(String id) {
        return fruitRepository.findById(id);
    }

    public Fruit updateFruit(String id, Fruit fruitDetails) {
        return fruitRepository.findById(id).map(fruit -> {
            fruit.setName(fruitDetails.getName());
            fruit.setColor(fruitDetails.getColor());
            fruit.setOrigin(fruitDetails.getOrigin());
            fruit.setPrice(fruitDetails.getPrice());
            return fruitRepository.save(fruit);
        }).orElseGet(() -> {
            fruitDetails.setId(id);
            return fruitRepository.save(fruitDetails);
        });
    }

    public void deleteFruit(String id) {
        fruitRepository.findById(id).ifPresent(fruit -> {
            fruit.setDeleted(true);
            fruitRepository.save(fruit);
        });
    }

    private FruitResponse toFruitResponse(Fruit fruit) {
        return FruitResponse.builder()
                .id(fruit.getId())
                .name(fruit.getName())
                .color(fruit.getColor())
                .origin(fruit.getOrigin())
                .price(fruit.getPrice())
                .build();
    }


}
