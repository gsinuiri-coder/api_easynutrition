package com.tchelper.nutrition.resource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveSessionResource {
    @NotNull
    @NotBlank
    @Size(max = 100)
    private String link;

    public String getLink() {
        return link;
    }

    public SaveSessionResource setLink(String link) {
        this.link = link;
        return this;
    }
}
