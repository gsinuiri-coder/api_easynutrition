package com.thenews.nutrition.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thenews.common.domain.model.AuditModel;
import com.thenews.userprofile.domain.model.Customer;
import com.thenews.userprofile.domain.model.Nutricionist;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name="advices")
public class Advice extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private boolean active;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Customer customer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "score_id", referencedColumnName = "id")
    @JsonIgnore
    private Score score;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = { CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "advice_diets",
            joinColumns = {@JoinColumn(name = "advice_id")},
            inverseJoinColumns = {@JoinColumn(name = "diet_id")})
    @JsonIgnore
    private List<Diet> diets;

    public boolean isTaggedWith(Diet diet) {
        return (this.getDiets().contains(diet));
    }

    public Advice tagWith(Diet diet) {
        if(!this.isTaggedWith(diet)) {
            this.getDiets().add(diet);
        }
        return this;
    }

    public Advice unTagWith(Diet diet) {
        if(this.isTaggedWith(diet)) {
            this.getDiets().remove(diet);
        }
        return this;
    }

    public Long getId() {
        return id;
    }

    public Advice setId(Long id) {
        this.id = id;
        return this;
    }

    public boolean isActive() {
        return active;
    }

    public Advice setActive(boolean active) {
        this.active = active;
        return this;
    }

    public Score getScore() {
        return score;
    }

    public Advice setScore(Score score) {
        this.score = score;
        return this;
    }

    public List<Diet> getDiets() {
        return diets;
    }

    public Advice setDiets(List<Diet> diets) {
        this.diets = diets;
        return this;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Advice setCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }
}
