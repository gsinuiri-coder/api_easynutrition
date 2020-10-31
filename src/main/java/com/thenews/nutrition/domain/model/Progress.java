package com.thenews.nutrition.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import com.thenews.common.domain.model.AuditModel;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name="progress")
public class Progress extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Double weight;

    @NotNull
    @Lob
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "advice_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Advice advice;


    public Long getId() {
        return id;
    }

    public Progress setId(Long id) {
        this.id = id;
        return this;
    }

    public Double getWeight() {
        return weight;
    }

    public Progress setWeight(Double weight) {
        this.weight = weight;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Progress setDescription(String description) {
        this.description = description;
        return this;
    }

    public Advice getAdvice() {
        return advice;
    }

    public Progress setAdvice(Advice advice) {
        this.advice = advice;
        return this;
    }
}
