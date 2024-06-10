package com.test.backend.fruit_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class SpringBootFruitApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootFruitApiApplication.class, args);
	}

}
