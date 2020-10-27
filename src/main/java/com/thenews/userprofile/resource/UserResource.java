package com.thenews.userprofile.resource;

import com.thenews.common.domain.model.AuditModel;

public class UserResource extends AuditModel {

    private Long id;
    private String userName;
    private String password;

    public Long getId() {
        return id;
    }

    public UserResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public UserResource setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserResource setPassword(String password) {
        this.password = password;
        return this;
    }
}
