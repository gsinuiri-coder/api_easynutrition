package com.thenews.nutrition.domain.model;

import com.sun.istack.NotNull;
import com.thenews.common.domain.model.AuditModel;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "sessiondetails")
public class SessionDetail extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotNull
    @Size(max = 50)
    @NaturalId
    private  String State;


}
