package com.tchelper.EasyNutrition.domain.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="sessions")
public class Session extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(unique = true)
    @NotNull
    private Date start_at;

    @NotNull
    private Date end_at;

    @NotNull
    private String link;

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
}
