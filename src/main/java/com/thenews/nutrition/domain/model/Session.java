package com.thenews.nutrition.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import com.thenews.common.domain.model.AuditModel;
import com.thenews.userprofile.domain.model.User;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="sessions")
public class Session extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Date start_at;

    @NotNull
    private Date end_at;

    @NotNull
    private String link;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    public Long getId() {
        return id;
    }

    public Session setId(Long id) {
        this.id = id;
        return this;
    }

    public Date getStart_at() {
        return start_at;
    }

    public Session setStart_at(Date start_at) {
        this.start_at = start_at;
        return this;
    }

    public Date getEnd_at() {
        return end_at;
    }

    public Session setEnd_at(Date end_at) {
        this.end_at = end_at;
        return this;
    }

    public String getLink() {
        return link;
    }

    public Session setLink(String link) {
        this.link = link;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Session setUser(User user) {
        this.user = user;
        return this;
    }
}
