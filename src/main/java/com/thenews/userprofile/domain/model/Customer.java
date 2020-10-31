package com.thenews.userprofile.domain.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="customers")
public class Customer extends User {

    private String typeFeeding;
    private boolean isAthlete;

    public String getTypeFeeding() {
        return typeFeeding;
    }

    public Customer setTypeFeeding(String typeFeeding) {
        this.typeFeeding = typeFeeding;
        return this;
    }

    public boolean isAthlete() {
        return isAthlete;
    }

    public Customer setAthlete(boolean athlete) {
        isAthlete = athlete;
        return this;
    }
}
