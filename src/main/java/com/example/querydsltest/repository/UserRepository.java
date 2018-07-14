package com.example.querydsltest.repository;

import com.example.querydsltest.model.Department;
import com.example.querydsltest.model.User;
import com.github.tennaito.rsql.jpa.JpaCriteriaQueryVisitor;
import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import cz.jirutka.rsql.parser.ast.RSQLVisitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 * Created by Смена on 14.07.2018.
 */
@Repository
public class UserRepository {
    private EntityManager entityManager;
    private JpaRepository<User, Integer> jpaRepository;


    @Autowired
    public UserRepository(EntityManager entityManager, JpaRepository jpaRepository) {
        this.entityManager = entityManager;
        this.jpaRepository = jpaRepository;
    }

    @Transactional
    public List<User> getUserByQueryString(String query) {
        RSQLVisitor<CriteriaQuery<User>, EntityManager> visitor = new JpaCriteriaQueryVisitor<>();
        Node root = new RSQLParser().parse(query);
        CriteriaQuery<User> criteriaQuery = root.accept(visitor, entityManager);
        List<User> users = entityManager.createQuery(criteriaQuery).getResultList();
        return users;
    }

    @Transactional
    public void init() {
        User user = new User("John", "Doe", 30);
        Department department = new Department("sales");
        user.addDepartment(department);
        entityManager.persist(department);
        jpaRepository.save(user);
    }
}
