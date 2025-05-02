package com.fitness.challenge.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.fitness.challenge.api.*")
public class FintessChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(FintessChallengeApplication.class, args);
	}

}
