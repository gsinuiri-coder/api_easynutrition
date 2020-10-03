package com.tchelper.nutrition.resource;


import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveUserResource {
    @NotNull
    @NotBlank
    @Size(max = 100)
    private String name;

    @NotNull
    @NotBlank
    @Size(max = 250)
    private String lastName;

    public String getName() {
        return name;
    }

    public SaveUserResource setName(String name) {
        this.name = name;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public SaveUserResource setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
}
