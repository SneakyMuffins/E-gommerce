package com.egommerce.demo.model.Category;

import com.egommerce.demo.annotation.ExcludeUpdate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "category")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ExcludeUpdate
    @Column(name = "entity_id")
    private Long id;

    @Column(name = "name", nullable = false)
    @NotBlank(message = "Category name is mandatory")
    private String name;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
