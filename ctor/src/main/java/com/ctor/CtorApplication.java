package com.ctor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
@EnableJpaAuditing
@SpringBootApplication
public class CtorApplication {

	public static void main(String[] args) {
		SpringApplication.run(CtorApplication.class, args);
	}

}
