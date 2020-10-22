package com.thenews.nutrition;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EasyNutritionApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasyNutritionApplication.class, args);
	}

}
