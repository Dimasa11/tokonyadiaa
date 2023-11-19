package com.enigma.tokonyadiaa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class TokonyadiaaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TokonyadiaaApplication.class, args);
	}

}
