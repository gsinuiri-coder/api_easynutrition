package com.thenews.userprofile.domain.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name="nutriocionists")
public class Nutricionist extends User {

    private String university;

    private Short seniorYear;

    private String linkCV;

    private String bank;

    private String accountNumber;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = { CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "nutricionist_tags",
            joinColumns = {@JoinColumn(name = "nutricionist_id")},
            inverseJoinColumns = {@JoinColumn(name = "specialty_id")})
    @JsonIgnore
    private List<Specialty> specialties;

    public boolean isTaggedWith(Specialty specialty) {
        return (this.getSpecialties().contains(specialty));
    }

    public Nutricionist tagWith(Specialty specialty) {
        if(!this.isTaggedWith(specialty)) {
            this.getSpecialties().add(specialty);
        }
        return this;
    }

    public Nutricionist unTagWith(Specialty specialty) {
        if(this.isTaggedWith(specialty)) {
            this.getSpecialties().remove(specialty);
        }
        return this;
    }

    public String getUniversity() {
        return university;
    }

    public Nutricionist setUniversity(String university) {
        this.university = university;
        return this;
    }

    public Short getSeniorYear() {
        return seniorYear;
    }

    public Nutricionist setSeniorYear(Short seniorYear) {
        this.seniorYear = seniorYear;
        return this;
    }

    public String getLinkCV() {
        return linkCV;
    }

    public Nutricionist setLinkCV(String linkCV) {
        this.linkCV = linkCV;
        return this;
    }

    public String getBank() {
        return bank;
    }

    public Nutricionist setBank(String bank) {
        this.bank = bank;
        return this;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Nutricionist setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public List<Specialty> getSpecialties() {
        return specialties;
    }

    public Nutricionist setSpecialties(List<Specialty> specialties) {
        this.specialties = specialties;
        return this;
    }
}
