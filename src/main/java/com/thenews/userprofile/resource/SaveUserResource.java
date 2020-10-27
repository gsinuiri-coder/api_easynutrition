package com.thenews.userprofile.resource;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveUserResource {

    @NotNull
    @NotBlank
    @Size(max = 80)
    private String userName;

    @NotNull
    @NotBlank
    @Size(max = 80)
    private String password;

    public String getUserName() {
        return userName;
    }

    public SaveUserResource setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public SaveUserResource setPassword(String password) {
        this.password = password;
        return this;
    }
}
