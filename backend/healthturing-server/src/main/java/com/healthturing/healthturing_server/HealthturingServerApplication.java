package com.healthturing.healthturing_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpaht:application.properties")
public class HealthturingServerApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(HealthturingServerApplication.class, args);

		
	}

}
