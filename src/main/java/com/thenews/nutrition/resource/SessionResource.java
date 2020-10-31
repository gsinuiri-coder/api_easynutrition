package com.thenews.nutrition.resource;

import com.thenews.common.domain.model.AuditModel;

import java.util.Date;

public class SessionResource extends AuditModel {

    private Long id;
    private Date StartAt;
    private String link;


    public Long getId() {
        return id;
    }

    public SessionResource setId(Long id) {
        this.id = id;
        return this;
    }

    public Date getStartAt() {
        return StartAt;
    }

    public SessionResource setStartAt(Date startAt) {
        StartAt = startAt;
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
