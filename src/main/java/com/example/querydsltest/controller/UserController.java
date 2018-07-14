package com.example.querydsltest.controller;

import com.example.querydsltest.model.User;
import com.example.querydsltest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final UserRepository repository;

    @Autowired
    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = "/users")
    @ResponseBody
    public List<User> getUsersByRequestParams(@RequestParam(value = "query") String query) {
        System.out.println(query);
        List<User> users = repository.getUserByQueryString(query);
        System.out.println(users);
        return users;
    }
}
