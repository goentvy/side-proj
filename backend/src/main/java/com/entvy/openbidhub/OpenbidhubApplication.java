package com.entvy.openbidhub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OpenbidhubApplication {
	public static void main(String[] args) {
        SpringApplication.run(OpenbidhubApplication.class, args);
	}
}
