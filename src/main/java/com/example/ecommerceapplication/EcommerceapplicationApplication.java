package com.example.ecommerceapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EcommerceapplicationApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceapplicationApplication.class, args);
	}

}
