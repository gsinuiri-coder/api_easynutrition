package com.thenews.nutrition.resource;

import com.thenews.common.domain.model.AuditModel;

public class AdviceResource extends AuditModel {

    private Long id;
    private boolean active;

    public Long getId() {
        return id;
    }

    public AdviceResource setId(Long id) {
        this.id = id;
        return this;
    }

    public boolean isActive() {
        return active;
    }

    public AdviceResource setActive(boolean active) {
        this.active = active;
        return this;
    }
}
