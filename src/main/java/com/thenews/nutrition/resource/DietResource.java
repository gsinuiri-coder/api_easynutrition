package com.thenews.nutrition.resource;

import com.thenews.nutrition.domain.model.AuditModel;

public class DietResource extends AuditModel {

    private Long id;
    private String title;
    private String description;

    public Long getId() {
        return id;
    }

    public DietResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public DietResource setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public DietResource setDescription(String description) {
        this.description = description;
        return this;
    }
}
