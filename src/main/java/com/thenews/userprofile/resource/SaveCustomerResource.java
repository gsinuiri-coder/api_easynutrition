package com.thenews.userprofile.resource;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveCustomerResource {

    @NotNull
    @Size(max = 80)
    private String userName;

    @NotNull
    @Size(max = 80)
    private String password;

    @NotNull
    @Size(max = 100)
    private String name;

    @NotNull
    @Size(max = 100)
    private String lastName;


    public String getUserName() {
        return userName;
    }

    public SaveCustomerResource setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public SaveCustomerResource setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getName() {
        return name;
    }

    public SaveCustomerResource setName(String name) {
        this.name = name;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public SaveCustomerResource setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
}
