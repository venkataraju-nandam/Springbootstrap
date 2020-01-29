package com.poc.springbootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.poc.springbootstrap.repository") 
@EntityScan("com.poc.springbootstrap.entitymodel")
@SpringBootApplication
public class SpringbootStrapApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootStrapApplication.class, args);
    }
}

//https://www.baeldung.com/spring-boot-start
