package com.divyendu.covidbatchapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Main Entry into code
 */
@SpringBootApplication
@EnableScheduling
public class CovidbatchappApplication {

	public static void main(String[] args) {
		SpringApplication.run(CovidbatchappApplication.class, args);
	}

}
