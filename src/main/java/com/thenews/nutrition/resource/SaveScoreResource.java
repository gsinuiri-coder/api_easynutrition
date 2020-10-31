package com.thenews.nutrition.resource;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

public class SaveScoreResource {

    @NotNull
    private Short star;

    @Lob
    private String description;

    public Short getStar() {
        return star;
    }

    public SaveScoreResource setStar(Short star) {
        this.star = star;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SaveScoreResource setDescription(String description) {
        this.description = description;
        return this;
    }
}
