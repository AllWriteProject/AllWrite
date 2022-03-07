package com.AW;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AWApplication {

	public static void main(String[] args) {
		SpringApplication.run(AWApplication.class, args);
	}

}
