package com.thenews.userprofile.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thenews.common.domain.model.AuditModel;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name="specialties")
public class Specialty extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 100)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            mappedBy = "specialties")
    @JsonIgnore
    private List<Nutricionist> nutricionists;

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

    public List<Nutricionist> getNutricionists() {
        return nutricionists;
    }

    public Specialty setNutricionists(List<Nutricionist> nutricionists) {
        this.nutricionists = nutricionists;
        return this;
    }
}
