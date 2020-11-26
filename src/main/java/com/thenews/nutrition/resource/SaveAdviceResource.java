package com.thenews.nutrition.resource;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public class SaveAdviceResource {

    @NotNull
    private boolean active;

    public boolean isActive() {
        return active;
    }

    public SaveAdviceResource setActive(boolean active) {
        this.active = active;
        return this;
    }
}
