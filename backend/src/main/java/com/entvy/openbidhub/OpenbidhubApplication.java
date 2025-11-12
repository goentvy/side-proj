package com.entvy.openbidhub;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan("com.entvy.openbidhub.mapper")
public class OpenbidhubApplication {
	public static void main(String[] args) {
        SpringApplication.run(OpenbidhubApplication.class, args);
	}
}
