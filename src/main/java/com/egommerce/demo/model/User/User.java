package com.egommerce.demo.model.User;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "entity_id")
    private Long id;

    @Column(name = "username", nullable = false)
    @NotBlank(message = "User name is mandatory")
    private String username;

    @Column(name = "password_hash", nullable = false)
    @NotBlank(message = "Password is mandatory")
    private String password;

    @Column(name = "email", nullable = false)
    @Email(message = "Invalid Email address provided")
    @NotBlank(message = "Email is mandatory")
    private String email;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private Timestamp updatedAt;

    public User() {}

    public User(@JsonProperty("username") String username,
                @JsonProperty("password") String password,
                @JsonProperty("email") String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Long getId() {
        return id;
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

    public void setPassword(String password) {
        this.password = password;
    }
}
