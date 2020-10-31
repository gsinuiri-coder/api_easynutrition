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

    private String linkCV;

    private String bank;

    private String accountNumber;


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
}
