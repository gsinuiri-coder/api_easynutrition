package com.thenews.nutrition.resource;

import com.thenews.nutrition.domain.model.AuditModel;

public class UserResource extends AuditModel {

    private Long id;
    private String name;
    private String lastName;

    public Long getId() {
        return id;
    }

    public UserResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserResource setName(String name) {
        this.name = name;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserResource setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
}
