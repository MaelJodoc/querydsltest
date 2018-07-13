package com.example.querydsltest;

import com.example.querydsltest.repository.UserRepositoryWithRsqlJpa;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class QuerydsltestApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(QuerydsltestApplication.class, args);
        UserRepositoryWithRsqlJpa repository = context.getBean(UserRepositoryWithRsqlJpa.class);
        repository.init();
    }
}
