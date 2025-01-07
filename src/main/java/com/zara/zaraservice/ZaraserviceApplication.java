package com.zara.zaraservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.zara.zaraservice.entity")
@EnableJpaRepositories(basePackages = "com.zara.zaraservice.repository")
public class ZaraserviceApplication {
	public static void main(String[] args) {
		SpringApplication.run(ZaraserviceApplication.class, args);
	}
}

