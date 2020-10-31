package com.thenews.userprofile.resource;

import com.thenews.common.domain.model.AuditModel;

public class SpecialtyResource extends AuditModel {
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public SpecialtyResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public SpecialtyResource setName(String name) {
        this.name = name;
        return this;
    }
}
