package com.ctor;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
@EnableJpaAuditing
@SpringBootApplication
public class CtorApplication {

    @PostConstruct
    void started() {
        // timezone UTC 셋팅
        TimeZone.setDefault(TimeZone.getTimeZone("KST"));
    }
	public static void main(String[] args) {
		SpringApplication.run(CtorApplication.class, args);
	}

}
