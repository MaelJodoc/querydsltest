package com.example.querydsltest.repository;

import com.example.querydsltest.model.Department;
import com.example.querydsltest.model.User;
import com.github.tennaito.rsql.jpa.JpaCriteriaQueryVisitor;
import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import cz.jirutka.rsql.parser.ast.RSQLVisitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 * Created by Смена on 13.07.2018.
 */
@Repository
interface UserRepositoryWithRsqlJpa extends JpaRepository<User, Integer> {

    private EntityManager entityManager;

    @Autowired
    public UserRepositoryWithRsqlJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<User> getUserByQueryString(String query) {
        RSQLVisitor<CriteriaQuery<User>, EntityManager> visitor = new JpaCriteriaQueryVisitor<>();
        Node root = new RSQLParser().parse(query);
        CriteriaQuery<User> criteriaQuery = root.accept(visitor, entityManager);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Transactional
    default void init() {
        User user = new User("John", "Doe", 30);
        Department department = new Department("sales");
        user.addDepartment(department);
        entityManager.persist(user);
    }
}
