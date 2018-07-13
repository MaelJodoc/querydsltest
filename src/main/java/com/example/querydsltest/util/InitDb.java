package com.example.querydsltest.util;

import com.example.querydsltest.QuerydsltestApplication;
import com.example.querydsltest.model.Department;
import com.example.querydsltest.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;


/**
 * Created by Смена on 13.07.2018.
 */

public class InitDb {
    @Transactional
    public static void init(EntityManager entityManager) {
        User user = new User("John", "Doe", 30);
        Department department = new Department("sales");
        user.addDepartment(department);
        entityManager.persist(user);
    }
}
