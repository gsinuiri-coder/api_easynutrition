package com.thenews.nutrition.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import com.thenews.common.domain.model.AuditModel;
import com.thenews.userprofile.domain.model.Nutricionist;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name="diets")
public class Diet extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotNull
    private String title;

    @NotNull
    @Lob
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "nutricionist_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Nutricionist nutricionist;

    public Long getId() {
        return id;
    }

    public Diet setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Diet setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Diet setDescription(String description) {
        this.description = description;
        return this;
    }

    public Nutricionist getNutricionist() {
        return nutricionist;
    }

    public Diet setNutricionist(Nutricionist nutricionist) {
        this.nutricionist = nutricionist;
        return this;
    }
}
