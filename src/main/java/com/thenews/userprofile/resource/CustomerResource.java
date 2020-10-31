package com.thenews.userprofile.resource;

import com.thenews.userprofile.domain.model.User;

public class CustomerResource extends User {

    private Long id;
    private String name;
    private String lastName;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public CustomerResource setId(Long id) {
        this.id = id;
        return this;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public CustomerResource setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public CustomerResource setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
}
