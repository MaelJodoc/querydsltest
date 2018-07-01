package com.example.querydsltest.repository;

import com.example.querydsltest.model.QUser;
import com.example.querydsltest.model.User;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

/**
 * Created by Смена on 01.07.2018.
 */
public interface UserRepository extends
        JpaRepository<User, Integer>,
        QuerydslPredicateExecutor<User>,
        QuerydslBinderCustomizer<QUser> {
    @Override
    default public void customize(QuerydslBindings bindings, QUser root) {
        bindings.bind(String.class).first((StringPath path, String value) -> path.containsIgnoreCase(value));

    }
}
