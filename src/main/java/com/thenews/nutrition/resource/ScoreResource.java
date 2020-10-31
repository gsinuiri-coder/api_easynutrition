package com.thenews.nutrition.resource;

import com.thenews.common.domain.model.AuditModel;

public class ScoreResource extends AuditModel {

    private Long id;
    private Short star;
    private String description;

    public Long getId() {
        return id;
    }

    public ScoreResource setId(Long id) {
        this.id = id;
        return this;
    }

    public Short getStar() {
        return star;
    }

    public ScoreResource setStar(Short star) {
        this.star = star;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ScoreResource setDescription(String description) {
        this.description = description;
        return this;
    }
}
