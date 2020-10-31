package com.thenews.nutrition.resource;


import javax.validation.constraints.NotNull;
import java.util.Date;

public class SaveSessionResource  {

    @NotNull
    private Date startAt;

    @NotNull
    private String link;


    public Date getStartAt() {
        return startAt;
    }

    public SaveSessionResource setStartAt(Date startAt) {
        this.startAt = startAt;
        return this;
    }

    public String getLink() {
        return link;
    }

    public SaveSessionResource setLink(String link) {
        this.link = link;
        return this;
    }
}
