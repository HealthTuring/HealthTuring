package com.healthturing.healthturing_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
@PropertySource("classpath:emailconfig.properties")
@EnableJpaRepositories("com.healthturing.healthturing_server.repositories")
public class HealthturingServerApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(HealthturingServerApplication.class, args);

		
	}

}
