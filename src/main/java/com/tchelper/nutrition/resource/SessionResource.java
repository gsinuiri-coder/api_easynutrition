package com.tchelper.nutrition.resource;

import com.tchelper.nutrition.domain.model.AuditModel;

public class SessionResource extends AuditModel {

    private Long id;
    private String link;

    public Long getId() {
        return id;
    }

    public SessionResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getLink() {
        return link;
    }

    public SessionResource setLink(String link) {
        this.link = link;
        return this;
    }
}
