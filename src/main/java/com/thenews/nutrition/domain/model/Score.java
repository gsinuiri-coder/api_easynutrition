package com.thenews.nutrition.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thenews.common.domain.model.AuditModel;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="scores")
public class Score extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Short star;

    @Lob
    private String description;

    @OneToOne(mappedBy = "score")
    @JsonIgnore
    private Advice advice;


    public Long getId() {
        return id;
    }

    public Score setId(Long id) {
        this.id = id;
        return this;
    }

    public Short getStar() {
        return star;
    }

    public Score setStar(Short star) {
        this.star = star;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Score setDescription(String description) {
        this.description = description;
        return this;
    }

    public Advice getAdvice() {
        return advice;
    }

    public Score setAdvice(Advice advice) {
        this.advice = advice;
        return this;
    }
}
