package com.thenews.nutrition.resource;


import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

public class SaveProgressResource {


    @NotNull
    private Double weight;

    @NotNull
    @Lob
    private String description;

    public Double getWeight() {
        return weight;
    }

    public SaveProgressResource setWeight(Double weight) {
        this.weight = weight;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SaveProgressResource setDescription(String description) {
        this.description = description;
        return this;
    }
}
