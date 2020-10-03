package com.tchelper.nutrition.resource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveDietResource {
    @NotNull
    @NotBlank
    @Size(max = 100)
    private String name;

    @NotNull
    @NotBlank
    @Size(max = 100)
    private String title;

    @NotNull
    @NotBlank
    @Size(max = 200)
    private String description;


    public String getName() {
        return name;
    }

    public SaveDietResource setName(String name) {
        this.name = name;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public SaveDietResource setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SaveDietResource setDescription(String description) {
        this.description = description;
        return this;
    }
}
