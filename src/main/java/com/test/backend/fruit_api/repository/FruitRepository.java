package com.test.backend.fruit_api.repository;

import com.test.backend.fruit_api.entity.Fruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FruitRepository extends JpaRepository<Fruit, String> {

    @Query("SELECT f FROM Fruit f WHERE f.deleted = false")
    List<Fruit> findAllActiveFruits();

}
