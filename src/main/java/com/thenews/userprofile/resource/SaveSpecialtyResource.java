package com.thenews.userprofile.resource;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveSpecialtyResource {

    @NotNull
    @Size(max = 100)
    protected String name;

    public String getName() {
        return name;
    }

    public SaveSpecialtyResource setName(String name) {
        this.name = name;
        return this;
    }
}
