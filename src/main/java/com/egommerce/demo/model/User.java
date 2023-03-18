package com.egommerce.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class User {
    @NotBlank(message = "User name is mandatory")
    private String username;
    @NotBlank(message = "Password is mandatory")
    private String password;

    @Email(message = "Invalid Email address provided")
    @NotBlank(message = "Email is mandatory")
    private String email;
    @NotNull
    private UUID userId;

    public User() {}

    public User(@JsonProperty("username") String username,
                @JsonProperty("password") String password,
                @JsonProperty("email") String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.userId = UUID.randomUUID();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public UUID getId() {
        return userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
