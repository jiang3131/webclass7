package com.example.webclass;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.webclass.repository")
public class WebclassApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebclassApplication.class, args);
    }

}
