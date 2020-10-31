package com.thenews.nutrition.resource;

import com.thenews.common.domain.model.AuditModel;

public class ProgressResource extends AuditModel {

    private Long id;
    private String weight;
    private String description;


    public Long getId() {
        return id;
    }

    public ProgressResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getWeight() {
        return weight;
    }

    public ProgressResource setWeight(String weight) {
        this.weight = weight;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProgressResource setDescription(String description) {
        this.description = description;
        return this;
    }
}
