package com.spring.java.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.spring.java.dao")
@EntityScan(basePackages = "com.spring.java.dao.model")
public class OfficeOneApplication {
    public static void main(String[] args) {
        SpringApplication.run(OfficeOneApplication.class, args);
    }
}
