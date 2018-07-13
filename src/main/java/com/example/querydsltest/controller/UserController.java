package com.example.querydsltest.controller;

import com.example.querydsltest.model.User;
import com.example.querydsltest.repository.UserRepositoryQueryDsl;
import com.example.querydsltest.repository.UserRepositoryWithRsqlJpa;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Смена on 01.07.2018.
 */

@RestController
public class UserController {
    /* private final UserRepositoryQueryDsl userRepositoryQueryDsl;

     @Autowired
     public UserController(UserRepositoryQueryDsl userRepositoryQueryDsl) {
         this.userRepositoryQueryDsl = userRepositoryQueryDsl;
     }

     @RequestMapping(method = RequestMethod.GET, value = "/users")
     @ResponseBody
     public Iterable<User> findAllByWebQuerydsl(
             @QuerydslPredicate(root = User.class) Predicate predicate) {
         return userRepositoryQueryDsl.findAll(predicate);
     }*/
    private final UserRepositoryWithRsqlJpa repository;

    @Autowired
    public UserController(UserRepositoryWithRsqlJpa repository) {
        this.repository = repository;
    }

    @GetMapping(value = "/users")
    @ResponseBody
    public List<User> getUsersByRequestParams(@RequestParam(value = "query") String query) {
        System.out.println(query);
        return repository.getUserByQueryString(query);
    }
}
