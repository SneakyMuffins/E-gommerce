package com.egommerce.demo.model.User;

import com.egommerce.demo.annotation.ExcludeUpdate;
import com.egommerce.demo.model.Address.Address;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "user")
public class User {
    @ExcludeUpdate
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "entity_id")
    private Long id;

    @JsonProperty("username")
    @Column(name = "username", nullable = false)
    @NotBlank(message = "User name is mandatory")
    private String username;

    @ExcludeUpdate
    @JsonProperty("password")
    @Column(name = "password_hash", nullable = false)
    @NotBlank(message = "Password is mandatory")
    private String password;

    @JsonProperty("email")
    @Column(name = "email", nullable = false)
    @Email(message = "Invalid Email address provided")
    @NotBlank(message = "Email is mandatory")
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses;

    @ExcludeUpdate
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @ExcludeUpdate
    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private Timestamp updatedAt;

    @JsonProperty("isAdmin")
    @Column(name = "is_admin", nullable = false)
    private Boolean isAdmin;

    public User() {}

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.isAdmin = false; // By default, a user is not an admin
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

    public Boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }
}
