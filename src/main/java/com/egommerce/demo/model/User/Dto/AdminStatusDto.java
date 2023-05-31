package com.egommerce.demo.model.User.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AdminStatusDto {
    @JsonProperty("isAdmin")
    private boolean isAdmin;

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}