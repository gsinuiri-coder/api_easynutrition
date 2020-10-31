package com.thenews.userprofile.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thenews.common.domain.model.AuditModel;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name="specialties")
public class Specialty extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @NotNull
    @Size(max = 100)
    protected String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "nutricionist_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Nutricionist nutricionist;


    public Long getId() {
        return id;
    }

    public Specialty setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Specialty setName(String name) {
        this.name = name;
        return this;
    }

    public Nutricionist getNutricionist() {
        return nutricionist;
    }

    public Specialty setNutricionist(Nutricionist nutricionist) {
        this.nutricionist = nutricionist;
        return this;
    }
}
