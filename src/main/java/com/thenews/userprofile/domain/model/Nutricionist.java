package com.thenews.userprofile.domain.model;


import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="nutriocionists")
public class Nutricionist extends User {

    private String university;

    private Short seniorYear;

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
}
