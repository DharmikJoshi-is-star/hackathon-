package com.tollapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {"com.tollapp.repository"})
@SpringBootApplication
public class TollAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TollAppApplication.class, args);
	}

}
