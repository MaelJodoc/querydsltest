package com.example.querydsltest.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Смена on 13.07.2018.
 */
@Entity
public class Department extends AbstractPersistable<Integer> {
    private String name;
    @ManyToMany(mappedBy = "departments")
    @JsonBackReference
    private List<User> users = new ArrayList<>();

    public Department() {
    }

    public Department(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
